package vn.learning.jwt.domain.request;

import lombok.Data;

@Data
public class RefreshTokenRequest {

    private String refreshToken;

}
