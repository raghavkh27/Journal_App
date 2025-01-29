package com.example.Journal.App.controller;

import com.example.Journal.App.entity.JournalEntity;
import com.example.Journal.App.entity.User;
import com.example.Journal.App.services.JournalServices;
import com.example.Journal.App.services.UserServices;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalServices journalServices;

    @Autowired
    private UserServices userServices;

    @GetMapping
    public ResponseEntity<?> getJournalEntriesOfUsers() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userServices.findByUsername(username);
        List<JournalEntity> all = user.getJournalEntries();
        if (!all.isEmpty() && all != null) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> createEntry(@RequestBody JournalEntity journalEntity) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            journalServices.saveEntry(journalEntity, username);
            System.out.println(journalEntity);
            return new ResponseEntity<>(journalEntity, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/id/{myid}")
    public ResponseEntity<JournalEntity> getJournalById(@PathVariable ObjectId myid) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userServices.findByUsername(username);
        List<JournalEntity> collect = user.getJournalEntries().stream().filter(journalEntity -> journalEntity.getId().equals(myid)).collect(Collectors.toList());
        if (collect.isEmpty()) {
            Optional<JournalEntity> x = journalServices.getJournalById(myid);
            if (x.isPresent()) {
                return new ResponseEntity<>(x.get(), HttpStatus.OK);
            }

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("/id//{myid}")
    public ResponseEntity<?> deleteEntryById(@PathVariable ObjectId myid) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        journalServices.deleteEntryById(myid, username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("id/{myid}")
    public ResponseEntity<?> updateEntryById(@PathVariable ObjectId myid, @RequestBody JournalEntity journalEntity) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userServices.findByUsername(username);
        List<JournalEntity> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(myid)).toList();
        if (collect.isEmpty()) {
            Optional<JournalEntity> x = journalServices.getJournalById(myid);
            if (x.isPresent()) {
                JournalEntity old = journalServices.getJournalById(myid).orElse(null);
                if (old != null) {
                    old.setTitle(journalEntity.getTitle() != null ? journalEntity.getTitle() : "");
                    old.setContent(journalEntity.getContent() != null ? journalEntity.getContent() : "");
                    journalServices.saveEntry(journalEntity);
                    return new ResponseEntity<>(journalEntity, HttpStatus.OK);
                }
            }

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}