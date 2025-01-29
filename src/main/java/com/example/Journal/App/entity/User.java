package com.example.Journal.App.entity;

import jakarta.annotation.Nonnull;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.ArrayList;
import java.util.List;

@Data
public class User {
    @Id
    private ObjectId id;
    @Indexed(unique = true)
    @Nonnull
    private String username;
    @Nonnull
    private String password;
    @DBRef
    private List<JournalEntity> journalEntries = new ArrayList<>();
    private List<String> roles;

}
