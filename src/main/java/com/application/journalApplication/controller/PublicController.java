package com.application.journalApplication.controller;

import com.application.journalApplication.entity.User;
import com.application.journalApplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @PostMapping
    public void createUser(@RequestBody User user)
    {
        userService.saveEntry(user);
    }

}
