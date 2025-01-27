package com.example.Journal.App.controller;

import com.example.Journal.App.entity.User;
import com.example.Journal.App.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserEntryController {
    @Autowired
    private UserServices userServices;

    @GetMapping
    public List<User> getAllUsers() {
        return userServices.getAllUsers();
    }
    @PostMapping
    public void createUser(@RequestBody User user) {
        userServices.saveEntry(user);
    }
    @PutMapping("/{username}")
    public ResponseEntity<?> updateUser(@RequestBody User user,  @PathVariable String username) {
        User userInDb = userServices.findByUsername(username);
        if(userInDb != null) {
            userInDb.setUsername(user.getUsername());
            userInDb.setPassword(user.getPassword());
            userServices.saveEntry(userInDb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
