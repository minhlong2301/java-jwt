package vn.learning.jwt.sercurity.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import vn.learning.jwt.model.User;

import java.util.Collection;
import java.util.Collections;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsImpl implements UserDetails {

    User user;

//    private Long id;
//
//    private String tenDangNhap;
//
//    @JsonIgnore
//    private String matKhau;
//
//    private String diaChi;
//
//    private String gioiTinh;
//
//    private String queQuan;
//
//    private String uuid;

//    public static UserDetailsImpl build(User user) {
//        return new UserDetailsImpl(
//                user.getId(),
//                user.getTenDangNhap(),
//                user.getMatKhau(),
//                user.getDiaChi(),
//                user.getGioiTinh(),
//                user.getQueQuan(),
//                user.getUuid()
//        );
//    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return user.getMatKhau();
    }

    @Override
    public String getUsername() {
        return user.getTenDangNhap();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
