package vn.learning.jwt.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "permission")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "UUID")
    private String uuid;

    @Column(name = "PERMISSION")
    private String permission;
}
