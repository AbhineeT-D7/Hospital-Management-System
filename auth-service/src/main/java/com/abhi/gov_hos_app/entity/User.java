package com.abhi.gov_hos_app.entity;

import com.abhi.gov_hos_app.entity.enums.Role;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.Transient;

@Data
@Entity
@Table(name="app_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AA_USER_SEQ")
    @SequenceGenerator(name = "AA_USER_SEQ", sequenceName = "AA_USER_SEQ", allocationSize = 1)
    private Long userId;

    private String name;
    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Transient
    private String token;
}