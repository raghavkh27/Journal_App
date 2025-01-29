package com.example.Journal.App.controller;

import com.example.Journal.App.entity.User;
import com.example.Journal.App.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")

public class PublicController {

    @Autowired
    private UserServices userServices;

    @GetMapping
    public String healthCheck() {
        return "OK";
    }
    @PostMapping("/create-user")
    public void createUser(@RequestBody User user) {
        userServices.saveEntry(user);
    }
}
