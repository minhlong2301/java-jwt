package vn.learning.jwt.jwt;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import vn.learning.jwt.sercurity.impl.UserDetailsImpl;

import java.util.Date;
import java.util.Objects;

@Slf4j
@Component
public class JwtProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${bezkoder.app.jwtSecret}")
    private String jwtSecret;

    @Value("${bezkoder.app.jwtExpirationMs}")
    private Integer jwtExpiration;

    public String createJwtToken(Authentication authentication) {
        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(userDetailsImpl.getUsername())
                .setId(String.valueOf(userDetailsImpl.getId()))
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpiration * 1000))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String createJwtTokenFromUserName(String userName) {
        return Jwts.builder()
                .setSubject(userName)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpiration * 1000))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public boolean validateJwtToken(String token) {
        try {
            log.info("===== " + Jwts.parser().setSigningKey(jwtSecret).parseClaimsJwt(token));
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJwt(token);
            return true;
        } catch (ExpiredJwtException e) {
            logger.error("Token hết hạn, " + e);
        } catch (UnsupportedJwtException e) {
            logger.error("Token này không được hỗ trợ, " + e);
        } catch (MalformedJwtException e) {
            logger.error("Token không đúng định dạng, " + e);
        } catch (SignatureException e) {
            logger.error("Token không xác thực được, " + e);
        } catch (IllegalArgumentException e) {
            logger.error("Token có kí tự trống không hợp lê, " + e);
        }
        return false;
    }

    public String getUserNameFromJwtToken(String token) {
        String userName = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJwt(token).getBody().getSubject();
        if (Objects.nonNull(userName)) {
            return userName;
        }
        return "";
    }

}
