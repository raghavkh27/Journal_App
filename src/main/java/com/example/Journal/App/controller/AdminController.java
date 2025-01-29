package com.example.Journal.App.controller;


import com.example.Journal.App.entity.User;
import com.example.Journal.App.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserServices userServices;

    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers() {
        List<User> all = userServices.getAllUsers();
        if(all.isEmpty()){
            return new ResponseEntity<>(all,HttpStatus.OK);
        }
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping
    public void  addUser(@RequestBody User user) {
        userServices.saveAdmin(user);
    }

}
