package com.application.journalApplication.service;


import com.application.journalApplication.entity.User;
import com.application.journalApplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void saveEntry(User user)
    {
        userRepository.save(user);
    }
    public List<User> getAll()
    {
        return userRepository.findAll();
    }

    public User findById(String id)
    {
        return userRepository.findById(id).orElse(null);
    }
    public void deleteById(String id)
    {
        userRepository.deleteById(id);
    }

    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
}
