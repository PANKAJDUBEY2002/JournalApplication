package com.application.journalApplication.controller;


import com.application.journalApplication.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {
    private Map<Long, JournalEntry>  journalEntries=new HashMap<>();

    @GetMapping
    public List<JournalEntry> getJournalEntries()
    {
        return new ArrayList<>(journalEntries.values());
    }
    @PostMapping
    public boolean addJournalEntry(@RequestBody JournalEntry myEntry)
    {
        journalEntries.put(myEntry.getId(),myEntry);
        return true;
    }

    @GetMapping("id/{myId}")
    public JournalEntry getJournalEntryById(@PathVariable Long myId)
    {
        return journalEntries.get(myId);
    }

    @DeleteMapping("id/{myId}")
    public JournalEntry deleteEntry(@PathVariable Long myId)
    {
        return journalEntries.remove(myId);
    }

    @PutMapping("id/{myId}")
    public  JournalEntry putEntry(@PathVariable Long myId, @RequestBody JournalEntry myEntry)
    {
        return journalEntries.put(myId,myEntry);
    }


}
