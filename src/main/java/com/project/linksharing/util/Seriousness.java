package com.project.linksharing.util;

public enum Seriousness {

    CASUAL("CASUAL" ),
    VERY_SERIOUS ("VERY_SERIOUS"),
    SERIOUS("SERIOUS");

    private final String value;

    Seriousness(final String value) {
        this.value = value;
    }

    public String getSeriousness() {
        return value;
    }
}
