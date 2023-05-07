package com.inchl.resourceserver.impl;

import com.inchl.resourceserver.entity.UserModelEntity;
import com.inchl.resourceserver.exception.UserNotFoundException;
import com.inchl.resourceserver.model.UserModel;
import com.inchl.resourceserver.repository.UserRepository;
import com.inchl.resourceserver.service.UserService;
import com.inchl.resourceserver.util.Constants;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.inchl.resourceserver.util.Constants.LOGGER;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserModel createUser(UserModel user) {
        UserModelEntity entity = userRepository.save(user.mapModelToEntity());
        return entity.toUserModelWithoutAllData();
    }

    @Override
    public UserModel updateUser(Long id, UserModel newUser) {
        UserModelEntity updatedUser =  userRepository.findById(id).map(user-> {
            if(newUser.getFullName() != null) user.setFullName(newUser.getFullName());
            if(newUser.getEmail() != null) user.setEmail(newUser.getEmail());
            if(newUser.getUserName() != null) user.setUserName(newUser.getUserName());
            if(newUser.getRole() != null) user.setRole(newUser.getRole());
            if(newUser.getImage() != null) user.setImage(newUser.getImage());
            if(newUser.getUserType() != null) user.setUserType(newUser.getUserType());
            if(newUser.getPassword() != null) user.setPassword(passwordEncoder.encode(newUser.getPassword()));
            return userRepository.save(user);
        }).orElseThrow(()->
                  new UserNotFoundException(id)
        );
        return updatedUser.toUserModelWithoutAllData();
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserModel getUser(Long id) {
         UserModelEntity userEntity = userRepository.findById(id).orElseThrow(()->{
             throw new UserNotFoundException(id);

         });

         return userEntity.toUserModelWithAllData();
    }

    @Override
    public UserModel login(UserModel user) {
        return null;
    }

    @Override
    public List<UserModel> getAllUsers() {
        return userRepository.findAll().stream().map(
                UserModelEntity::toUserModelWithAllData).collect(Collectors.toList());
    }

}
