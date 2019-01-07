package com.project.linksharing.model;

import javax.persistence.*;

@Entity
@DiscriminatorValue( "LinkResource" )
public class LinkResource  extends Resource{

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
