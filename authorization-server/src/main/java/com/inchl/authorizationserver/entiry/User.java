package com.inchl.authorizationserver.entiry;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="user_id")
    Long id;
    String fullName;
    @Column(name="user_name", nullable = false)
    String userName;
    @Column(name="password", nullable = false, length = 60)
    String password;
    @Column(name="email", nullable = false)
    String email;
    String role;
    String image;
    @Column(nullable = false)
    String userType;
    Long dateCreated;
    Boolean enabled = true;
}
