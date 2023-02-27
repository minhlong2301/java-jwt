package vn.learning.jwt.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.learning.jwt.domain.request.LoginRequest;
import vn.learning.jwt.domain.request.RefreshTokenRequest;
import vn.learning.jwt.domain.request.RegisterRequest;
import vn.learning.jwt.domain.response.ResponseGlobal;
import vn.learning.jwt.service.AuthService;
import vn.learning.jwt.service.ValidateService;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    private final ValidateService validateService;

    @PostMapping("/register")
    public ResponseEntity<ResponseGlobal<Object>> registerAccount(@Valid @RequestBody RegisterRequest registerRequest,
                                                                  BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.ok(validateService.getErrorValidate(result));
        }
        return ResponseEntity.ok(authService.registerAccount(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseGlobal<Object>> userLogin(@Valid @RequestBody LoginRequest loginRequest,
                                                            BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.ok(validateService.getErrorValidate(result));
        }
        return ResponseEntity.ok(authService.userLogin(loginRequest));
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<ResponseGlobal<Object>> refreshToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest,
                                                                BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.ok(validateService.getErrorValidate(result));
        }
        return ResponseEntity.ok(authService.refreshToken(refreshTokenRequest));
    }
}
