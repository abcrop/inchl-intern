package com.inchl.authorizationserver.controller;


import com.inchl.authorizationserver.entiry.User;
import com.inchl.authorizationserver.repository.UserRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/v1/")
public class SignupController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/createUser")
    public User createUser(@RequestBody User user) {
        LoggerFactory.getLogger(SignupController.class).error("Get password "+user.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        LoggerFactory.getLogger(SignupController.class).error("seted password "+user.getPassword());
        var newUser = userRepository.save(user);

        return newUser ;
    }
}
