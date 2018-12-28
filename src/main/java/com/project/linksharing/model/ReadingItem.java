package com.project.linksharing.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ReadingItem {

    @Id
    private Long readingItemId;
    private Resource resource;
    private User user;
    private Boolean isRead;

    public Long getReadingItemId() {
        return readingItemId;
    }

    public void setReadingItemId(Long readingItemId) {
        this.readingItemId = readingItemId;
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

    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }
}
