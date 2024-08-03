package com.example.journalapp.controller;
import com.example.journalapp.entity.JournalEntry;
import com.example.journalapp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journalV2")
public class journalEntryControllerV2 {
    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public List<JournalEntry> getAll(){

        return journalEntryService.getAll();
    }
    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry userEntry){
       userEntry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(userEntry);
        return  true;
    }
    //path variable and request parameter
    @GetMapping("id/{myId}")
    public JournalEntry getJournalId(@PathVariable String myId){
        return journalEntryService.findById(myId).orElse(null);
    }
    @DeleteMapping("id/{myId}")
    public boolean removeJournalId(@PathVariable  String myId){
         journalEntryService.deleteById(myId);

         return  true;
    }
    @PutMapping("id/{myId}")
    public JournalEntry updateJournalById(@PathVariable String myId, @RequestBody JournalEntry myEntry){
       JournalEntry oldEntry=journalEntryService.findById(myId).orElse(null);
       if(oldEntry!=null){
           oldEntry.setTitle(myEntry.getTitle()!=null && myEntry.getTitle().equals("")? myEntry.getTitle() : oldEntry.getTitle());
           oldEntry.setContent(myEntry.getContent()!=null && !myEntry.equals("")? myEntry.getContent(): oldEntry.getContent());
       }
        journalEntryService.saveEntry(myEntry);
        return  oldEntry;

    }


}
