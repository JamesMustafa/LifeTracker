package com.james.LifeTracker.web.controller;

import com.james.LifeTracker.service.UserDetailsServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    private final UserDetailsServiceImpl userService;
    private final ModelMapper modelMapper;

    public UserController(UserDetailsServiceImpl userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/login")
    @PreAuthorize("isAnonymous()")
    public String login(){
        return "user/login";
    }
}
