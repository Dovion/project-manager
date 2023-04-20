package ru.dovion.projectmanager.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.dovion.projectmanager.exception.InvalidDataException;
import ru.dovion.projectmanager.model.Status;
import ru.dovion.projectmanager.model.dto.ProjectOutDto;
import ru.dovion.projectmanager.model.dto.TaskDto;
import ru.dovion.projectmanager.model.dto.TaskOutDto;
import ru.dovion.projectmanager.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/user-api")
public class UserController {

    private final UserService userService;

    @PostMapping("/task")
    @Operation(summary = "Создание новой задачи")
    public TaskOutDto createTask(@RequestBody @Validated TaskDto task, BindingResult result) {
        if (result.hasErrors()) {
            throw new InvalidDataException(result);
        }
        return userService.createTask(task);
    }

    @PatchMapping("/task/status")
    @Operation(summary = "Обновление статуса задачи")
    public TaskOutDto updateTaskStatus(@RequestParam Long id, @RequestParam Status status) {
        return userService.updateTaskStatus(id, status);
    }

    @DeleteMapping("/task")
    @Operation(summary = "Удаление задачи")
    public void deleteTask(@RequestParam Long id) {
        userService.deleteTask(id);
    }

    @GetMapping("/task")
    @Operation(summary = "Получение всех задач")
    public List<TaskOutDto> getAllTasks() {
        return userService.getAllTasks();
    }

    @GetMapping("/project")
    @Operation(summary = "Получение всех проектов")
    public List<ProjectOutDto> getAllProjects() {
        return userService.getAllProjects();
    }
}
