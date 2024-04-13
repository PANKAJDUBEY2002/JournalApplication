package com.application.journalApplication.controller;


import com.application.journalApplication.entity.JournalEntry;
import com.application.journalApplication.entity.User;
import com.application.journalApplication.service.JournalEntryService;
import com.application.journalApplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;
    @Autowired
    private UserService userService;


    @GetMapping
    public ResponseEntity<?> getJournalEntries()
    {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        User user=userService.findByUserName(userName);
        List<JournalEntry> all=user.getJournalEntries();
        if (all != null && !all.isEmpty()) {
                return new ResponseEntity<>(all, HttpStatus.OK);
            }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping
    public ResponseEntity<?> addJournalEntry(@RequestBody JournalEntry myEntry)
    {
        try {
            Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
            String userName=authentication.getName();
            User user=userService.findByUserName(userName);

            myEntry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(myEntry,user);
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
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        User user=userService.findByUserName(userName);
        List<JournalEntry> collect=user.getJournalEntries().stream().filter(x->x.getId().equals(myId)).collect(Collectors.toList());
if(!collect.isEmpty()) {
    Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);
    if (journalEntry.isPresent()) {
        return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
    }
}
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> deleteEntry(@PathVariable String myId)
    {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        User user=userService.findByUserName(userName);

        journalEntryService.deleteById(myId,user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("id/{myId}")
    public  ResponseEntity<?> putEntry(@PathVariable String myId, @RequestBody JournalEntry myEntry)
  {
      Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
      String userName=authentication.getName();
      User user=userService.findByUserName(userName);

      myEntry.setDate(LocalDateTime.now());
      List<JournalEntry> collect=user.getJournalEntries().stream().filter(x->x.getId().equals(myId)).collect(Collectors.toList());
      if(!collect.isEmpty()) {
          JournalEntry old = journalEntryService.findById(myId).orElse(null);
          if (old != null) {
              old.setTitle(myEntry.getTitle() != null && !myEntry.getTitle().equals("") ? myEntry.getTitle() : old.getTitle());
              old.setContent(myEntry.getContent() != null && !myEntry.getContent().equals("") ? myEntry.getContent() : old.getContent());
              journalEntryService.saveEntry(old);
              return new ResponseEntity<>(old, HttpStatus.OK);
          }
      }
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);

   }




}
