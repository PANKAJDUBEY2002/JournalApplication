package com.application.journalApplication.controller;


import com.application.journalApplication.entity.JournalEntry;
import com.application.journalApplication.service.JournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;


    @GetMapping
    public List<JournalEntry> getJournalEntries()
    {
        return journalEntryService.getAll();
    }
    @PostMapping
    public boolean addJournalEntry(@RequestBody JournalEntry myEntry)
    {
        myEntry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(myEntry);
        return true;
    }

    @GetMapping("id/{myId}")
    public JournalEntry getJournalEntryById(@PathVariable String myId)
    {
        return journalEntryService.findById(myId).orElse(null);
    }

    @DeleteMapping("id/{myId}")
    public boolean deleteEntry(@PathVariable String myId)
    {
        journalEntryService.deleteById(myId);
        return true;
    }

    @PutMapping("id/{myId}")
    public  JournalEntry putEntry(@PathVariable String myId, @RequestBody JournalEntry myEntry)
    {
        myEntry.setDate(LocalDateTime.now());
        JournalEntry old=journalEntryService.findById(myId).orElse(null);
        if(old!=null)
        {
            old.setTitle(myEntry.getTitle()!=null&&!myEntry.getTitle().equals("")?myEntry.getTitle(): old.getTitle());
            old.setContent(myEntry.getContent()!=null&&!myEntry.getContent().equals("")?myEntry.getContent(): old.getContent());
        }


        journalEntryService.saveEntry(old);
        return old;
    }




}
