package com.application.journalApplication.repository;

import com.application.journalApplication.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRepositoryImpl {
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<User> getAllUser() {
        Query query=new Query();
        query.addCriteria(Criteria.where("email").exists(true));
        query.addCriteria(Criteria.where("sentimentAnalysis").is(true));
        List<User> user=mongoTemplate.find(query,User.class);

    }
}
