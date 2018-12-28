package com.project.linksharing.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ResourceRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resourceRatingId;
    private Resource resource;
    private User user;
    private Integer score;

    public Long getResourceRatingId() {
        return resourceRatingId;
    }

    public void setResourceRatingId(Long resourceRatingId) {
        this.resourceRatingId = resourceRatingId;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
