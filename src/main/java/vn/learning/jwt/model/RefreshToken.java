package vn.learning.jwt.model;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;

@Data
@Entity
@Table(name = "REFRESH_TOKEN")
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, unique = true)
    private Long id;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "TOKEN")
    private String token;

    @Column(name = "EXPIRYDATE")
    private String expiryDate;

}
