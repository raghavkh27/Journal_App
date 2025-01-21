package com.example.Journal.App.controller;

import com.example.Journal.App.entity.JournalEntity;
import com.example.Journal.App.repository.JournalRepo;
import com.example.Journal.App.services.JournalServices;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalServices journalServices;
    @Autowired
    private JournalRepo journalRepo;

    @GetMapping
    public ResponseEntity<?> getJournalEntityMap() {
         return new ResponseEntity<>(journalRepo.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public boolean createEntry(@RequestBody JournalEntity journalEntity) {
        journalEntity.setDateTime(LocalDateTime.now());
        journalServices.saveEntry(journalEntity);
        System.out.println(journalEntity);
        return true;
    }

    @GetMapping("/id/{myid}")
    public ResponseEntity<JournalEntity> getJournalById(@PathVariable ObjectId myid) {
       Optional<JournalEntity> x = journalServices.getJournalById(myid);
       if(x.isPresent()){
           return new ResponseEntity<>(x.get(), HttpStatus.OK);
    }
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);}


    @DeleteMapping("/id/{myid}")
    public ResponseEntity<?> deleteEntryById(@PathVariable ObjectId myid ) {
         journalServices.deleteEntryById(myid);
         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("id/{myid}")
    public ResponseEntity<?> updateEntryById(@PathVariable ObjectId myid, @RequestBody JournalEntity journalEntity) {
        JournalEntity old = journalServices.getJournalById(myid).orElse(null);
        if(old != null) {
            old.setTitle(journalEntity.getTitle() != null ? journalEntity.getTitle() : "");
            old.setContent(journalEntity.getContent() != null ? journalEntity.getContent() : "");
            journalServices.saveEntry(journalEntity);
            return new ResponseEntity<>(journalEntity,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
