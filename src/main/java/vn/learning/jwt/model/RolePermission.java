package vn.learning.jwt.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "role_permission")
public class RolePermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ROLE_UUID")
    private String role_uuid;

    @Column(name = "PERMISSION_UUID")
    private String permission_uuid;
}
