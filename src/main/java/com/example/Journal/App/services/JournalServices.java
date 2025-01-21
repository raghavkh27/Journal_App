package com.example.Journal.App.services;

import com.example.Journal.App.entity.JournalEntity;
import com.example.Journal.App.repository.JournalRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JournalServices {

    @Autowired
    private JournalRepo journalRepo;

    public void saveEntry (JournalEntity journalEntity){
        journalRepo.save(journalEntity);
    }
    public List<JournalEntity> getAllJournals(){
        return journalRepo.findAll();
    }

    public Optional<JournalEntity> getJournalById(ObjectId id) {
        return journalRepo.findById(id);
    }

    public void deleteEntryById(ObjectId id){
        journalRepo.deleteById(id);
    }
    public void updateEntryById(ObjectId id, JournalEntity journalEntity){
        journalEntity.setDateTime(journalEntity.getDateTime());
    }
}
