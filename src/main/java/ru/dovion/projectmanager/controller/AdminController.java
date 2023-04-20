package ru.dovion.projectmanager.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.dovion.projectmanager.exception.BusinessException;
import ru.dovion.projectmanager.exception.InvalidDataException;
import ru.dovion.projectmanager.model.dto.ProjectDto;
import ru.dovion.projectmanager.model.dto.ProjectOutDto;
import ru.dovion.projectmanager.model.dto.TaskDto;
import ru.dovion.projectmanager.model.dto.TaskOutDto;
import ru.dovion.projectmanager.service.AdminService;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/admin-api")
public class AdminController {

    private final AdminService adminService;

    @PatchMapping("/task")
    @Operation(summary = "Обновление задачи (Доступно только для администратора)")
    public TaskOutDto updateTask(@RequestBody @Validated TaskDto task, @RequestParam Long id, BindingResult result) {
        if (result.hasErrors()) {
            throw new InvalidDataException(result);
        }
        return adminService.updateTask(task, id);
    }

    @DeleteMapping("/task")
    @Operation(summary = "Удаление задачи (Доступно только для администратора)")
    public void deleteTask(@RequestParam Long id) {
        adminService.deleteTask(id);
    }

    @PostMapping("/project")
    @Operation(summary = "Создание нового проекта (Доступно только для администратора)")
    public ProjectOutDto createProject(@RequestBody @Validated ProjectDto project, BindingResult result) {
        if (result.hasErrors()) {
            throw new InvalidDataException(result);
        }
        return adminService.createProject(project);
    }

    @PatchMapping("/project")
    @Operation(summary = "Обновление информации проекта (Доступно только для администратора)")
    public ProjectOutDto updateProject(@RequestBody @Validated ProjectDto project, @RequestParam Long id)
            throws BusinessException {
        return adminService.updateProject(project, id);
    }

    @DeleteMapping("/project")
    @Operation(summary = "Удаление проекта (Доступно только для администратора)")
    public void deleteProject(@RequestParam Long id) {
        adminService.deleteProject(id);
    }
}
