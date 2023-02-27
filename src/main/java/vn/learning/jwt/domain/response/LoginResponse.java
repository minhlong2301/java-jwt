package vn.learning.jwt.domain.response;

import lombok.Data;

@Data
public class LoginResponse {

    private String name;

    private String type = "Bearer";

    private String accessToken;

    private String role;

    private String refreshToken;
}
