package com.javarush.ivannikov.spring.project1.controllers;

import com.javarush.ivannikov.spring.project1.domain.Status;
import com.javarush.ivannikov.spring.project1.domain.Task;
import com.javarush.ivannikov.spring.project1.dto.TaskInfo;
import com.javarush.ivannikov.spring.project1.service.TaskServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/")
public class TaskController {

    private final TaskServiceImpl taskService;

    public TaskController(TaskServiceImpl taskService) {
        this.taskService = taskService;
    }

    @GetMapping()
    public String getAllTasks(Model model,
                              @RequestParam(value = "page", defaultValue = "1") int page,
                              @RequestParam(value = "limit", defaultValue = "10") int limit) {
        List<Task> tasks = taskService.getAllTask((page - 1) * limit, limit);
        model.addAttribute("tasks", tasks);
        model.addAttribute("current_page", page);
        model.addAttribute("taskInfo", new TaskInfo());
        int totalPages = (int) Math.ceil(1.0 * taskService.getAllCount() / limit);
        if (totalPages > 1) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("page_numbers", pageNumbers);
        }
        return "index";
    }

    @PostMapping("/{id}")
    public String edit(Model model, @PathVariable("id") Long id) {
        List<Status> statusList = new ArrayList<>();
        statusList.add(Status.IN_PROGRESS);
        statusList.add(Status.DONE);
        statusList.add(Status.PAUSED);
        model.addAttribute("task", taskService.getById(id));
        model.addAttribute("statusList", statusList);
        return "edit";
    }

    @PatchMapping("/{id}")
    public String edit(Model model, @PathVariable("id") Long id, @ModelAttribute("task") TaskInfo info) {
        if (Objects.isNull(id) || id <= 0) {
            throw new RuntimeException("Invalid id");
        }
        Task taskEdit = taskService.edit(id, info.getDescription(), info.getStatus());
        model.addAttribute("taskEdit", taskEdit);
        return "redirect:/";
    }

    @PostMapping()
    public String add(@ModelAttribute("task") TaskInfo task) {
        taskService.create(task.getDescription(), task.getStatus());
        return "redirect:/";
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        if (Objects.isNull(id) || id < 0) {
            throw new RuntimeException("Invalid id");
        }
        taskService.delete(id);
        return "redirect:/";
    }
}
