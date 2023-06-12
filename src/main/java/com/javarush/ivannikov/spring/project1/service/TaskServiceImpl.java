package com.javarush.ivannikov.spring.project1.service;

import com.javarush.ivannikov.spring.project1.dao.TaskDAOImpl;
import com.javarush.ivannikov.spring.project1.domain.Status;
import com.javarush.ivannikov.spring.project1.domain.Task;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class TaskServiceImpl implements TaskService {

    private final TaskDAOImpl taskDAO;

    public TaskServiceImpl(TaskDAOImpl taskDAO) {
        this.taskDAO = taskDAO;
    }


    @Override
    public List<Task> getAllTask(int offset, int limit) {
        return taskDAO.getAllTask(offset, limit);
    }

    @Override
    public Integer getAllCount() {
        return taskDAO.getAllCount();
    }

    @Override
    @Transactional
    public Task edit(Long id, String description, Status status) {
        Task task = taskDAO.getById(id);
        if(Objects.isNull(task)) {
            throw new RuntimeException("Not found");
        }
        task.setDescription(description);
        task.setStatus(status);
        taskDAO.saveOrUpdate(task);
        return task;
    }

    @Override
    public Task getById(Long id) {
        return taskDAO.getById(id);
    }

    @Override
    public Task create(String description, Status status) {
        Task task = new Task();
        task.setDescription(description);
        task.setStatus(status);
        taskDAO.saveOrUpdate(task);
        return task;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Task task = taskDAO.getById(id);
        if(Objects.isNull(task)) {
            throw new RuntimeException("Not found");
        }
        taskDAO.delete(task);
    }
}
