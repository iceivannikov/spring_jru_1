package com.javarush.ivannikov.spring.project1.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "task", schema = "public")
public class Task {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "description")
    private String description;
    @Enumerated(value = EnumType.ORDINAL)
    @Column(name = "status")
    private Status status;

    public Task() {
    }

    public Task(Long id, String description, Status status) {
        this.id = id;
        this.description = description;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
