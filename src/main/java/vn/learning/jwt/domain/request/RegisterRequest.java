package vn.learning.jwt.domain.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class RegisterRequest {

    @NotBlank(message = "Username không được để trống")
    private String userName;

    @NotBlank(message = "Password không được để trống")
    private String passWord;

    @NotNull(message = "Sex không được để trống")
    private String sex;
}
