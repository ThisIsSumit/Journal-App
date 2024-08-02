package com.example.journalapp.service;

import com.example.journalapp.entity.JournalEntry;
import com.example.journalapp.repository.JournaEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JournalEntryService {
    @Autowired
    private JournaEntryRepository journaEntryRepository;

    public void saveEntry(JournalEntry journalEntry){
        journaEntryRepository.save(journalEntry);

    }


}
// controller --> service --->repository
