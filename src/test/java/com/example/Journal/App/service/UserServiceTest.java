package com.example.Journal.App.service;

import com.example.Journal.App.entity.User;
import com.example.Journal.App.repository.UserRepo;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserRepo userRepo;

    @Test
    public void testFindByUsername() {
        assertNotNull(userRepo.findByUsername("Raghav"));
    }
}