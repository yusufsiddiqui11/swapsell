package com.stackroute.authenticationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.stackroute.authenticationservice.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndPassword(String email, String password);
}