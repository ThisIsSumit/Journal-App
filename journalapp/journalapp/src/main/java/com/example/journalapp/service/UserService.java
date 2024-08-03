package com.example.journalapp.service;

import com.example.journalapp.entity.JournalEntry;
import com.example.journalapp.entity.User;
import com.example.journalapp.repository.JournalEntryRepository;
import com.example.journalapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void saveEntry(User newEntry){
        userRepository.save(newEntry);

    }
    public List<User> getAll(){
        return userRepository.findAll();

    }
    public Optional<User> findById(String id){
        return  userRepository.findById(id);

    }
    public  void  deleteById(String id){
        userRepository.deleteById(id);

    }
    public  User findByUserName(String name){
        return userRepository.findByUserName(name);
    }


}
// controller --> service -->repository
