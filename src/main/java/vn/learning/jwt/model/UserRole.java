package vn.learning.jwt.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_role")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ROLE_UUID")
    private String role_uuid;

    @Column(name = "USER_UUID")
    private String user_uuid;
}
