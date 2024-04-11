package com.application.journalApplication.service;

import com.application.journalApplication.entity.JournalEntry;
import com.application.journalApplication.entity.User;
import com.application.journalApplication.repository.JournalEntryRepository;
import com.application.journalApplication.repository.UserRepository;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component

public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    public void saveEntry(JournalEntry journalEntry, User user)
    {
        user.getJournalEntries().add(journalEntry);
        journalEntryRepository.save(journalEntry);
        userService.saveEntry(user);

    }
    public void saveEntry(JournalEntry journalEntry)
    {

        journalEntryRepository.save(journalEntry);


    }

    public List<JournalEntry> getAll()
    {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(String id)
    {
        return journalEntryRepository.findById(id);
    }

    public void deleteById(String id,User user)
    {
        user.getJournalEntries().removeIf(x->x.getId().equals(id));
        userService.saveEntry(user);
        journalEntryRepository.deleteById(id);
    }

}
