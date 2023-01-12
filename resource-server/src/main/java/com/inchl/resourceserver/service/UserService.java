package com.inchl.resourceserver.service;


import com.inchl.resourceserver.model.UserModel;

import java.util.List;

public interface UserService {
    UserModel createUser(UserModel userModel);
    UserModel updateUser(Long id, UserModel userModel);
    void deleteUser(Long id);
    UserModel getUser(Long id);
    UserModel login(UserModel userModel);
    List<UserModel> getAllUsers();
}
