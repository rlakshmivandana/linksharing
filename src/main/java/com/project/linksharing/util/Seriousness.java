package com.project.linksharing.util;

public enum Seriousness {

    SERIOUSNESS_CASUAL("CASUAL" ),
    SERIOUSNESS_VERY_SERIOUS ("VERY_SERIOUS"),
    SERIOUSNESS_SERIOUS("SERIOUS");

    private final String value;

    Seriousness(final String value) {
        this.value = value;
    }


    public String getSeriousness() {
        return value;
    }
}
