package com.example.journalapp.controller;

import com.example.journalapp.entity.JournalEntry;
import com.example.journalapp.service.JournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journalV2")
public class journalEntryControllerV2 {
    @Autowired
    private JournalEntryService journalEntryService;
    @GetMapping
    public List<JournalEntry> getAll(){
        return null;
    }
    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry userEntry){
        journalEntryService.saveEntry(userEntry);
        return  true;
    }
    //path variable and request parameter
    @GetMapping("id/{myId}")
    public JournalEntry getJournalId(@PathVariable  Long myId){
        return null;
    }
    @DeleteMapping("id/{myId}")
    public JournalEntry removeJournalId(@PathVariable  Long myId){
        return null;
    }
    @PutMapping("id/{myId}")
    public JournalEntry updateJournalById(@PathVariable Long myId, @RequestBody JournalEntry myEntry){
        return  null;
    }


}
