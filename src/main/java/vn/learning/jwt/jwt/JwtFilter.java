package vn.learning.jwt.jwt;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import vn.learning.jwt.sercurity.impl.UserDetailsServiceImpl;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;


public class JwtFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);

//    private final JwtProvider jwtProvider;
//
//    private final UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = getJwtTokenFromHeader(httpServletRequest);
            if (jwtProvider.validateJwtToken(token)) {
                String userName = jwtProvider.getUserNameFromJwtToken(token);
                UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(userName);
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, null);
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } catch (Exception e) {
            logger.error("Không thể set user authentication");
        }
    }

    private String getJwtTokenFromHeader(HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("Authorization");
        if (Objects.nonNull(authorization) && authorization.startsWith("Bearer")) {
            return authorization.substring(7);
        }
        return "";
    }
}

