package vn.learning.jwt.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import vn.learning.jwt.exeption.RefreshTokenExeption;
import vn.learning.jwt.model.RefreshToken;
import vn.learning.jwt.repositoryy.RefreshTokenRepository;
import vn.learning.jwt.repositoryy.UserRepository;

import java.time.Instant;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    @Value("${bezkoder.app.jwtRefreshExpirationMs}")
    private Long timeRefreshTokenExpirationMs;

    private final RefreshTokenRepository refreshTokenRepository;

    private final UserRepository userRepository;

    public RefreshToken createRefreshToken(String userId) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setUserId(userId);
        refreshToken.setExpiryDate(String.valueOf(Instant.now().plusMillis(timeRefreshTokenExpirationMs)));
        refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    public RefreshToken verifyExpiration(RefreshToken refreshToken) {
        log.info("ExpiryDate " + refreshToken.getExpiryDate());
        log.info("now " + Instant.now());
        if (refreshToken.getExpiryDate().equals(String.valueOf(Instant.now()))) {
            refreshTokenRepository.delete(refreshToken);
            throw new RefreshTokenExeption(refreshToken.getToken(), "Refresh token was expired. Please make a new signin request");
        }
        return refreshToken;
    }

    public int deleteUserRefreshToken(String userId) {
        return refreshTokenRepository.deleteByUserId(userId);
    }

}
