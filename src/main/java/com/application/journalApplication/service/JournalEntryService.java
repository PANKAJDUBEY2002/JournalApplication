package com.application.journalApplication.service;

import com.application.journalApplication.entity.JournalEntry;
import com.application.journalApplication.entity.User;
import com.application.journalApplication.repository.JournalEntryRepository;
import com.application.journalApplication.repository.UserRepository;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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


    @Transactional
    public void saveEntry(JournalEntry journalEntry, User user)
    {
        try {
            user.getJournalEntries().add(journalEntry);
            journalEntryRepository.save(journalEntry);
            userService.saveMyEntry(user);
        }
        catch (Exception e)
        {
            System.out.println(e);
            throw new RuntimeException("An error occured while saving the entry",e);
        }

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
    @Transactional
    public void deleteById(String id,User user)
    {
        boolean removed=false;
        try {
            removed=user.getJournalEntries().removeIf(x->x.getId().equals(id));
            if(removed)
            {
                userService.saveMyEntry(user);
                journalEntryRepository.deleteById(id);
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
            throw new RuntimeException("An error occcured during save a entry",e);
        }

    }

}
