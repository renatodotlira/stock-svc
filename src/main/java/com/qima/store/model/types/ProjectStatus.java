package com.qima.store.model.types;

public enum ProjectStatus {

    PLANNING("planning"),
    DOING("doing"),
    RELEASING("releasing"),
    CANCELED("canceled"),
    DONE("done");

    private final String status;

    ProjectStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
