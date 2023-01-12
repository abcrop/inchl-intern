package com.inchl.resourceserver.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Entity is a representation of the database table and it's column.
* Thus each entity must have similar Table, Column name as a names of variables.
* */
@Entity
@Data
@Table(name="users")
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String email;
    private String userType;
    @Column(length = 60)
    private String password;
    private String role;
    private boolean enabled = false;

}
