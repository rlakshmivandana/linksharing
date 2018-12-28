package com.project.linksharing.util;

public enum Visibility {

    PUBLIC("PUBLIC" ),
    PRIVATE ( "PRIVATE");

    private final String status;

    Visibility(final String status) {
        this.status = status;
    }


    public String getVisibility() {
        return status;
    }
}
