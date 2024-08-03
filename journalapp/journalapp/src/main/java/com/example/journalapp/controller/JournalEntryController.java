package com.example.journalapp.controller;

import com.example.journalapp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {
   private final Map<String, JournalEntry> journalEntries=new HashMap<>();
   @GetMapping
   public List<JournalEntry> getAll(){
       return new ArrayList<>(journalEntries.values());
   }
   @PostMapping
   public boolean createEntry(@RequestBody JournalEntry userEntry){
       journalEntries.put(userEntry.getId(), userEntry);
      return  true;
   }
   //path variable and request parameter
    @GetMapping("id/{myId}")
    public JournalEntry getJournalId(@PathVariable  String myId){
            return journalEntries.get(myId);
    }
    @DeleteMapping("id/{myId}")
    public JournalEntry removeJournalId(@PathVariable  String myId){
        return journalEntries.remove(myId);
    }
    @PutMapping("id/{myId}")
    public JournalEntry updateJournalById(@PathVariable String myId, @RequestBody JournalEntry myEntry){
       return  journalEntries.put(myId,myEntry);
    }


}
