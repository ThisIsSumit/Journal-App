package com.example.journalapp.repository;

import com.example.journalapp.entity.JournalEntry;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournaEntryRepository  extends MongoRepository <JournalEntry,String>{


}
