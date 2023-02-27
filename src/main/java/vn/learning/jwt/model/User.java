package vn.learning.jwt.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, unique = true)
    private Long id;

    @Column(name = "TEN_DANGNHAP")
    private String tenDangNhap;

    @Column(name = "MAT_KHAU")
    private String matKhau;

    @Column(name = "DIA_CHI")
    private String diaChi;

    @Column(name = "GIOI_TINH")
    private String gioiTinh;

    @Column(name = "QUE_QUAN")
    private String queQuan;

    @Column(name = "UUID")
    private String uuid;

}
