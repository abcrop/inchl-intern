package com.inchl.client.service;

import com.inchl.client.entity.User;
import com.inchl.client.model.UserModel;

public interface UserService {
    User registerUser(UserModel userModel);
    User findUserByEmail(String email);
    String validateVerificationToken(String token);
}
