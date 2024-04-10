package com.application.journalApplication.controller;


import com.application.journalApplication.entity.JournalEntry;
import com.application.journalApplication.service.JournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;


    @GetMapping
    public ResponseEntity<?> getJournalEntries()
    {
        List<JournalEntry>all=journalEntryService.getAll();
        if(all!=null&&!all.isEmpty())
        {
            return new ResponseEntity<>(all,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping
    public ResponseEntity<?> addJournalEntry(@RequestBody JournalEntry myEntry)
    {
        try {
            myEntry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(myEntry);
            return new ResponseEntity<>(myEntry,HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable String myId)
    {
        Optional<JournalEntry> journalEntry=journalEntryService.findById(myId);
        if(journalEntry.isPresent()) {
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> deleteEntry(@PathVariable String myId)
    {
        journalEntryService.deleteById(myId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("id/{myId}")
    public  ResponseEntity<?> putEntry(@PathVariable String myId, @RequestBody JournalEntry myEntry)
    {
        myEntry.setDate(LocalDateTime.now());
        JournalEntry old=journalEntryService.findById(myId).orElse(null);
        if(old!=null)
        {
            old.setTitle(myEntry.getTitle()!=null&&!myEntry.getTitle().equals("")?myEntry.getTitle(): old.getTitle());
            old.setContent(myEntry.getContent()!=null&&!myEntry.getContent().equals("")?myEntry.getContent(): old.getContent());
            journalEntryService.saveEntry(old);
            return new ResponseEntity<>(old,HttpStatus.OK);
        }
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);


    }




}
