package com.example.journalapp.controller;

import com.example.journalapp.entity.User;
import com.example.journalapp.service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping
    private List<User> getAllUsers(){
        return  userService.getAll();
    }
    @PostMapping
    private void createUser(@RequestBody User user){
        userService.saveEntry(user);
    }
    @PutMapping("/{userName}")
    private ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String userName){
            User userInDb=userService.findByUserName(userName);
            if(userInDb !=null){
                userInDb.setUserName(user.getUserName());
                userInDb.setPassword(user.getPassword());
                userService.saveEntry(user);
            }
            return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
