package com.javarush.ivannikov.spring.project1.dto;

import com.javarush.ivannikov.spring.project1.domain.Status;

public class TaskInfo {

    private String description;

    private Status status;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
