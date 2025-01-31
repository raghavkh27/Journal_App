package com.example.Journal.App.services;

import com.example.Journal.App.entity.JournalEntity;
import com.example.Journal.App.entity.User;
import com.example.Journal.App.repository.UserRepo;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserServices {

    @Autowired
    private UserRepo userRepo;

    private static final Logger logger = LoggerFactory.getLogger(UserServices.class);

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void saveNewUser ( User user){
        userRepo.save(user);
    }

    public void saveEntry ( User user){
        try{
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of("USER"));
        userRepo.save(user);}
        catch (Exception e){
            logger.error(e.getMessage());
        }
    }
    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    public Optional<User> getUserById(ObjectId id) {
        return userRepo.findById(id);
    }

    public void deleteUserById(ObjectId id){
        userRepo.deleteById(id);
    }
    public void updateUserById(ObjectId id, JournalEntity journalEntity){
        journalEntity.setDateTime(journalEntity.getDateTime());
    }
    public User findByUsername(String username){
        return userRepo.findByUsername(username);
    }

    public void deleteUserByUsername(String username) {
        userRepo.deleteUserByUsername(username);
    }
    public void saveUser(User user){
        userRepo.save(user);
    }

    public void saveAdmin(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of("USER", "ADMIN"));
        userRepo.save(user);
    }
}
