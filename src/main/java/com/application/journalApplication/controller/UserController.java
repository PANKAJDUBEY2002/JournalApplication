package com.application.journalApplication.controller;


import com.application.journalApplication.api.WeatherResponse;
import com.application.journalApplication.entity.User;
import com.application.journalApplication.service.UserService;
import com.application.journalApplication.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private WeatherService weatherService;

    @GetMapping
    public List<User> getAllUser()
    {
        return userService.getAll();
    }


    @PutMapping()
    public ResponseEntity<User> updateUser(@RequestBody User user)
    {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        User userInDb=userService.findByUserName(userName);
        if(userInDb!=null)
        {
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userService.saveUserEntry(userInDb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/remove")
    public ResponseEntity<?> deleteByUserName() {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        userService.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/weather")
    public ResponseEntity<?> getWeather()
    {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        String greeting="";
       WeatherResponse london = weatherService.getWeather("London");
        if(london!=null)
        {
            greeting="temperature in celcius "+String.valueOf(london.getCurrent().getTempc());
        }
        return new ResponseEntity<>("Hii "+userName+" "+greeting,HttpStatus.OK);
    }



}
