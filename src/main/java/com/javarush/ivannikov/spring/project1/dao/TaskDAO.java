package com.javarush.ivannikov.spring.project1.dao;

import com.javarush.ivannikov.spring.project1.domain.Task;

import java.util.List;

public interface TaskDAO {
    List<Task> getAllTask(int offset, int limit);

    Integer getAllCount();

    Task getById(Long id);

    void saveOrUpdate(Task task);

    void delete(Task task);
}
