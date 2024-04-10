package com.application.journalApplication.repository;

import com.application.journalApplication.entity.JournalEntry;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;



public interface JournalEntryRepository extends MongoRepository<JournalEntry,String> {
}
