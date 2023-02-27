package vn.learning.jwt.domain.response;

import lombok.Data;

@Data
public class RefreshTokenResponse {

    private String accessToken;

    private String refreshToken;
}
