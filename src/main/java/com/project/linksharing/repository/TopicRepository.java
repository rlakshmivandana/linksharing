package com.project.linksharing.repository;


import com.project.linksharing.model.Topic;
import com.project.linksharing.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {

    public Optional<Topic> findByName(String name);
}
