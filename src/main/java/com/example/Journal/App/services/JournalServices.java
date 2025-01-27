package com.example.Journal.App.services;

import com.example.Journal.App.entity.JournalEntity;
import com.example.Journal.App.entity.User;
import com.example.Journal.App.repository.JournalRepo;
import org.bson.types.ObjectId;
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
        User user = userServices.findByUsername(username);
        journalEntity.setDateTime(LocalDateTime.now());
        JournalEntity saved = journalRepo.save(journalEntity);
        user.getJournalEntries().add(saved);
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

    public void deleteEntryById(ObjectId id, String username){
        User user = userServices.findByUsername(username);
        JournalEntity saved = journalRepo.findById(id).get();
        user.getJournalEntries().remove(saved);
        journalRepo.deleteById(id);
    }
    public void updateEntryById(ObjectId id, JournalEntity journalEntity){
        journalEntity.setDateTime(journalEntity.getDateTime());
    }
}
