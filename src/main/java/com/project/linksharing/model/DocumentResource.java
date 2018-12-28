package com.project.linksharing.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DocumentResource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long documentResourceId;

    private String filePath;

    public Long getDocumentResourceId() {
        return documentResourceId;
    }

    public void setDocumentResourceId(Long documentResourceId) {
        this.documentResourceId = documentResourceId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
