package com.inchl.resourceserver.model;

import com.inchl.resourceserver.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Each model is what we send and see as a data in the frontend
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
    private Long id;
    private String fullName;
    private String email;
    private String userType;
    private String role;

    public UserModel mapEntityToModel(UserEntity userEntity) {
        UserModel userModel = new UserModel();
        userModel.setId(userEntity.getId());
        userModel.setEmail(userEntity.getEmail());
        userModel.setFullName(userEntity.getFullName());
        userModel.setUserType(userEntity.getUserType());
        userModel.setRole(userEntity.getRole());
        return userModel;
    }
}
