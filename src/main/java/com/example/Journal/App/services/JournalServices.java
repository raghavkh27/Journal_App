package com.example.Journal.App.services;

import com.example.Journal.App.entity.JournalEntity;
import com.example.Journal.App.entity.User;
import com.example.Journal.App.repository.JournalRepo;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalServices {

    @Autowired
    private JournalRepo journalRepo;
    @Autowired
    private UserServices userServices;



    @Transactional
    public void saveEntry (JournalEntity journalEntity, String username){
        try {
            User user = userServices.findByUsername(username);
            journalEntity.setDateTime(LocalDateTime.now());
            JournalEntity saved = journalRepo.save(journalEntity);
            user.getJournalEntries().add(saved);
            userServices.saveUser(user);
        }catch (Exception e){
            throw new RuntimeException("AN error occured while saving journal",e);
        }
    }
    public void saveEntry (JournalEntity journalEntity){
        journalRepo.save(journalEntity);
    }
    public List<JournalEntity> getAllJournals(){
        return journalRepo.findAll();
    }

    public Optional<JournalEntity> getJournalById(ObjectId id) {
        return journalRepo.findById(id);
    }

    @Transactional
    public void deleteEntryById(ObjectId id, String username){
        User user = userServices.findByUsername(username);
        boolean removed = user.getJournalEntries().removeIf(entry -> entry.getId().equals(id));
        if(removed){
            userServices.saveUser(user);
            journalRepo.deleteById(id);
        }
    }
    public void updateEntryById(ObjectId id, JournalEntity journalEntity){
        journalEntity.setDateTime(journalEntity.getDateTime());
    }
}
