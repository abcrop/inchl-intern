package com.inchl.resourceserver.controller;

import com.inchl.resourceserver.entity.UserEntity;
import com.inchl.resourceserver.exception.UserNotFoundException;
import com.inchl.resourceserver.model.UserModel;
import com.inchl.resourceserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public String grettings(){
        return "Hello Ab";
    }

    @PostMapping("/createUser")
    public UserModel createUser(@RequestBody UserModel user) {
        return userService.createUser(user);
    }

    @PostMapping("/login")
    public UserModel login(@RequestBody UserModel userEntity) {
        return userService.login(userEntity);
    }

    @GetMapping("/getAllUsers")
    public List<UserModel> getAllUsers(){
        return userService.getAllUsers();
    }

    @PutMapping("/updateUser/{id}")
    public UserModel updateUser(@PathVariable Long id, @RequestBody UserModel userModel){
        return userService.updateUser(id, userModel);
    }

    @GetMapping("/getUser/{id}")
    public UserModel getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @DeleteMapping("/deleteUser/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
