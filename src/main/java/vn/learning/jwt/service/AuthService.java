package vn.learning.jwt.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.learning.jwt.domain.request.LoginRequest;
import vn.learning.jwt.domain.request.RefreshTokenRequest;
import vn.learning.jwt.domain.request.RegisterRequest;
import vn.learning.jwt.domain.response.LoginResponse;
import vn.learning.jwt.domain.response.RefreshTokenResponse;
import vn.learning.jwt.domain.response.ResponseGlobal;
import vn.learning.jwt.jwt.JwtProvider;
import vn.learning.jwt.model.RefreshToken;
import vn.learning.jwt.model.User;
import vn.learning.jwt.repositoryy.RefreshTokenRepository;
import vn.learning.jwt.repositoryy.RoleRepository;
import vn.learning.jwt.repositoryy.UserRepository;
import vn.learning.jwt.repositoryy.UserRoleRepository;
import vn.learning.jwt.sercurity.impl.UserDetailsImpl;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthService {

    @Value("e3c3f1c0-b2d1-11ed-afa1-0242ac120002")
    private String uuidRoleUser;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    private final UserRoleRepository userRoleRepository;

    private final AuthenticationManager authenticationManager;

    private final JwtProvider jwtProvider;

//    private final RefreshTokenService refreshTokenService;

    private final RefreshTokenRepository refreshTokenRepository;

    public String generateUUID() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }

    @Transactional
    public ResponseGlobal<Object> registerAccount(RegisterRequest registerRequest) {
        if (userRepository.existsByTenDangNhap(registerRequest.getUserName())) {
            return ResponseGlobal.builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .message("Tên đăng nhập đã tồn tại")
                    .data(null)
                    .build();
        }
        User user = new User();
        user.setUuid(generateUUID());
        user.setTenDangNhap(registerRequest.getUserName());
        user.setMatKhau(passwordEncoder.encode(registerRequest.getPassWord()));
        user.setGioiTinh(registerRequest.getSex());
        userRepository.save(user);
        return ResponseGlobal.builder()
                .status(HttpStatus.OK.value())
                .message("Thành công")
                .data(user)
                .build();
    }

    public ResponseGlobal<Object> userLogin(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassWord()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();
        String token = jwtProvider.createJwtToken(userDetailsImpl);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setAccessToken(token);
        loginResponse.setName(userDetailsImpl.getUsername());
        return ResponseGlobal.builder()
                .status(HttpStatus.OK.value())
                .message("Thành công")
                .data(loginResponse)
                .build();
    }

//    public ResponseGlobal<Object> refreshToken(RefreshTokenRequest refreshTokenRequest) {
//        RefreshTokenResponse refreshTokenResponse = new RefreshTokenResponse();
//        Optional<RefreshToken> refreshToken = refreshTokenRepository.findByToken(refreshTokenRequest.getRefreshToken());
//        if (refreshToken.isPresent()) {
//            Optional<RefreshToken> refreshToken1 = Optional.of(refreshTokenRepository.findByToken(refreshTokenRequest.getRefreshToken()).get());
//            Optional<User> user = userRepository.findByUuid(refreshToken1.get().getUserId());
//            String token = jwtProvider.createJwtTokenFromUserName(user.get().getTenDangNhap());
//            refreshTokenResponse.setRefreshToken(refreshTokenRequest.getRefreshToken());
//            refreshTokenResponse.setAccessToken(token);
//            return ResponseGlobal.builder()
//                    .status(HttpStatus.OK.value())
//                    .message("Thành công")
//                    .data(refreshTokenResponse)
//                    .build();
//        } else {
//            return ResponseGlobal.builder()
//                    .status(HttpStatus.BAD_REQUEST.value())
//                    .message("RefreshToken không có trong DB")
//                    .data(null)
//                    .build();
//        }
//    }

}
