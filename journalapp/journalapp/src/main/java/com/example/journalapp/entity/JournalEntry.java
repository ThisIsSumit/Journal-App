package com.example.journalapp.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "journal_entries")
public class JournalEntry {
    @Id
    private String id;
    private  String title;
    private  String content;
    private LocalDateTime date;


}
