package com.javarush.ivannikov.spring.project1.service;

import com.javarush.ivannikov.spring.project1.domain.Status;
import com.javarush.ivannikov.spring.project1.domain.Task;

import java.util.List;

public interface TaskService {
    List<Task> getAllTask(int offset, int limit);

    Integer getAllCount();

    Task edit(Long id, String description, Status status);

    Task getById(Long id);

    Task create(String description, Status status);

    void delete(Long id);

}
