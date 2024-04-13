package com.application.journalApplication.controller;


import com.application.journalApplication.entity.User;
import com.application.journalApplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    UserService userService;
    @GetMapping
    public ResponseEntity<?> getAllUser()
    {
        List<User> all=userService.getAll();
        if(all!=null&&!all.isEmpty())
        {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping
    public void createUser(@RequestBody User user)
    {
        userService.saveAdmin(user);
    }
}
