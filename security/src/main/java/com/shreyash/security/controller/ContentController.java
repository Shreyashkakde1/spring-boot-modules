package com.shreyash.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContentController {

    @GetMapping("/home")
    public String handleWelcome(){
        return "Home";
    }

    @GetMapping("/admin/home")
    public String handleAdminHome(){
        return "Hello Admin";
    }

    @GetMapping("user/home")
    public String handleUserHome(){
        return "home user";
    }

}
