package com.application.journalApplication.repository;

import com.application.journalApplication.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {

    User findByUserName(String username);

}
