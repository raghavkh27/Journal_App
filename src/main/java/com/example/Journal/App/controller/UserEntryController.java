package com.example.Journal.App.controller;

import com.example.Journal.App.entity.JournalEntity;
import com.example.Journal.App.entity.User;
import com.example.Journal.App.repository.JournalRepo;
import com.example.Journal.App.repository.UserRepo;
import com.example.Journal.App.services.JournalServices;
import com.example.Journal.App.services.UserServices;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserEntryController {
    @Autowired
    private UserServices userServices;

    @GetMapping
    public ResponseEntity<Iterable<User>> getAllUsers() {

    }

}
