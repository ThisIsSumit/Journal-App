package com.example.journalapp.service;

import com.example.journalapp.entity.JournalEntry;
import com.example.journalapp.entity.User;
import com.example.journalapp.repository.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private  UserService userService;
    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){
        try{
            User newuser = userService.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            newuser.getJournalEntryList().add(saved);
            userService.saveEntry(newuser);
        } catch ( Exception e){
            throw new RuntimeException("An error occurred while saving the entry.",e);
        }

    }
    public void saveEntry(JournalEntry journalEntry){

        journalEntryRepository.save(journalEntry);

    }
    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();

    }
    public Optional<JournalEntry> findById(String id){
        return  journalEntryRepository.findById(id);

    }
    public  void  deleteById(String id, String userName){
        User user = userService.findByUserName(userName);
        user.getJournalEntryList().removeIf(x -> x.getId().equals(id));
        userService.saveEntry(user);
       journalEntryRepository.deleteById(id);

    }


}
// controller --> service -->repository
