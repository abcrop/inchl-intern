package com.inchl.resourceserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class CheckController {

    @GetMapping("/api/check")
    public String check(Principal principal) {
        return "Check Oauth2 " + principal.getName();
    }

}
