package com.project.linksharing.repository;

import com.project.linksharing.model.Subscription;
import com.project.linksharing.model.Topic;
import com.project.linksharing.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long>{
public Optional<Subscription> findByUserAndTopic(User user, Topic topic);
public List<Subscription> findAllByTopic(Topic topic);
    public List<Subscription> findAllByUser(User user);

}
