package com.inchl.client.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
    private String fullName;
    private String email;
    private String password;
    private String matchingPassword;
}
