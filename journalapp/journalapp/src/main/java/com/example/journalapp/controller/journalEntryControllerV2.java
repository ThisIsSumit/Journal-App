package com.example.journalapp.controller;
import com.example.journalapp.entity.JournalEntry;
import com.example.journalapp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> getAll(){
        List<JournalEntry> all= journalEntryService.getAll();
            if(all!=null && !all.isEmpty()){
                return new ResponseEntity<>(all,HttpStatus.OK);

            }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry userEntry){
       try {
            userEntry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(userEntry);
            return new ResponseEntity<>(userEntry, HttpStatus.CREATED);
        }
       catch (Exception e){
           return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
       }
    }
    //path variable and request parameter
    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getJournalId(@PathVariable String myId){
        Optional<JournalEntry>journalEntry= journalEntryService.findById(myId);
        if(journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>( HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> removeJournalId(@PathVariable  String myId){
         journalEntryService.deleteById(myId);

         return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("id/{myId}")
    public  ResponseEntity<?> updateJournalById(@PathVariable String myId, @RequestBody JournalEntry myEntry){
       JournalEntry oldEntry=journalEntryService.findById(myId).orElse(null);
       if(oldEntry!=null){
           oldEntry.setTitle(myEntry.getTitle()!=null && myEntry.getTitle().equals("")? myEntry.getTitle() : oldEntry.getTitle());
           oldEntry.setContent(myEntry.getContent()!=null && !myEntry.equals("")? myEntry.getContent(): oldEntry.getContent());
           journalEntryService.saveEntry(myEntry);
           return  new ResponseEntity<>(oldEntry,HttpStatus.OK);
       }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }


}
