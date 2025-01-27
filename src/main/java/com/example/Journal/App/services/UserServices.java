package com.example.Journal.App.services;

import com.example.Journal.App.entity.JournalEntity;
import com.example.Journal.App.entity.User;
import com.example.Journal.App.repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServices {

    @Autowired
    private UserRepo userRepo;

    public void saveEntry ( User user){
        userRepo.save(user);
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
}
