package com.stackroute.userservice.repository;

import com.stackroute.userservice.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserServiceRepository extends MongoRepository<User,Long> {
    Optional<User> findUserByEmail(String emailId);
    void deleteUserByEmail(String emailId);
}
