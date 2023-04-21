package ru.dovion.projectmanager.service;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.dovion.projectmanager.exception.BusinessException;
import ru.dovion.projectmanager.model.dto.ProjectDto;
import ru.dovion.projectmanager.model.dto.ProjectOutDto;
import ru.dovion.projectmanager.model.dto.TaskDto;
import ru.dovion.projectmanager.model.dto.TaskOutDto;

import java.util.List;

public interface AdminService {

    TaskOutDto updateTask(TaskDto task, Long id);

    void deleteTask(@RequestParam Long id);

    ProjectOutDto createProject(ProjectDto project);

    ProjectOutDto updateProject(@RequestBody ProjectDto project, Long id) throws BusinessException;

    void deleteProject(@RequestParam Long id);

    List<TaskOutDto> getAllTasks();
}
