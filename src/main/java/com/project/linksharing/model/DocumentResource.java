package com.project.linksharing.model;

import javax.persistence.*;

@Entity
@DiscriminatorValue( "DocumentResource" )
public class DocumentResource extends Resource {

    private String filePath;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
