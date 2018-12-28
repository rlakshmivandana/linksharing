package com.project.linksharing.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LinkResource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long linkResourceId;

    private String url;

    public Long getLinkResourceId() {
        return linkResourceId;
    }

    public void setLinkResourceId(Long linkResourceId) {
        this.linkResourceId = linkResourceId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
