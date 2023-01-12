package com.inchl.resourceserver.service;

import com.inchl.resourceserver.entity.UserEntity;
import com.inchl.resourceserver.exception.UserNotFoundException;
import com.inchl.resourceserver.model.UserModel;
import com.inchl.resourceserver.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserModel createUser(UserModel user) {
        UserEntity tempUserEntity = new UserEntity();
        BeanUtils.copyProperties(user, tempUserEntity);
        userRepository.save(tempUserEntity);
        return user;
    }

    @Override
    public UserModel updateUser(Long id, UserModel newUser) {
        UserEntity updatedUser =  userRepository.findById(id).map(user-> {
            user.setId(id);
            if(newUser.getFullName() != null) user.setFullName(newUser.getFullName());
            if(newUser.getEmail() != null) user.setEmail(newUser.getEmail());
            if(newUser.getUserType() != null) user.setUserType(newUser.getUserType());
            return userRepository.save(user);
        }).orElseThrow(()->
                  new UserNotFoundException(id)
        );
        return new UserModel().mapEntityToModel(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserModel getUser(Long id) {
         UserEntity userEntity = userRepository.findById(id).orElseThrow(()->{
             throw new UserNotFoundException(id);

         });
         return new UserModel().mapEntityToModel(userEntity);
    }

    @Override
    public UserModel login(UserModel user) {
        return null;
    }

    @Override
    public List<UserModel> getAllUsers() {
        return userRepository.findAll().stream().map(
                userEntity ->  new UserModel(
                        userEntity.getId(),
                        userEntity.getFullName(),
                        userEntity.getEmail(),
                        userEntity.getUserType(),
                        userEntity.getRole()
                )).collect(Collectors.toList());
    }



}
