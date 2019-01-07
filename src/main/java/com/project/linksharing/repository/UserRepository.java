package com.project.linksharing.repository;

import com.project.linksharing.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {
    public Optional<User> findByUsernameAndPassword(String username, String password);

}
