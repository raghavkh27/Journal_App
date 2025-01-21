package com.example.Journal.App.repository;

import com.example.Journal.App.entity.JournalEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JournalRepo extends MongoRepository<JournalEntity, ObjectId> {
    // Custom query methods (if needed) can be added here
}
