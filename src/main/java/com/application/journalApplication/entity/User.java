package com.application.journalApplication.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "users")
@Data
@NoArgsConstructor
public class User {

    @Id
    private String id;
    @Indexed(unique = true)
    @NonNull
    private String userName;
    @NonNull
    private String password;
    private String email;
    private String sentimentAnalysis;
     @DBRef
    private List<JournalEntry> journalEntries=new ArrayList<>();
     private List<String> roles;



}

