package com.example.journalapp.controller;
import com.example.journalapp.entity.JournalEntry;
import com.example.journalapp.entity.User;
import com.example.journalapp.service.JournalEntryService;
import com.example.journalapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class journalEntryController {
    @Autowired
    private JournalEntryService journalEntryService;
    @Autowired
    private UserService userService;


    @GetMapping("{userName}")
    public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String userName){
       User newuser = userService.findByUserName(userName);
        List<JournalEntry> all= newuser.getJournalEntryList();
            if(all!=null){
                return new ResponseEntity<>(all,HttpStatus.OK);

            }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @PostMapping("{userName}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry userEntry , @PathVariable String userName){

       try {

            userEntry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(userEntry,userName);
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
        return journalEntry.map(entry -> new ResponseEntity<>(entry, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @DeleteMapping("id/{userName}/{myId}")
    public ResponseEntity<?> removeJournalId(@PathVariable  String myId,@PathVariable String userName){
         journalEntryService.deleteById(myId,userName);

         return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("id/{userName}/{myId}")
    public  ResponseEntity<?> updateJournalById(
            @PathVariable String myId,
            @RequestBody JournalEntry myEntry,
            @PathVariable String userName
    ){
       JournalEntry oldEntry=journalEntryService.findById(myId).orElse(null);
       if(oldEntry!=null){
           oldEntry.setTitle(myEntry.getTitle().isEmpty() ? myEntry.getTitle() : oldEntry.getTitle());
           oldEntry.setContent(myEntry.getContent()!=null && !myEntry.equals("")? myEntry.getContent(): oldEntry.getContent());
           journalEntryService.saveEntry(myEntry);
           return  new ResponseEntity<>(oldEntry,HttpStatus.OK);
       }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }


}
