package ru.dovion.projectmanager.service;

import ru.dovion.projectmanager.model.Status;
import ru.dovion.projectmanager.model.dto.ProjectOutDto;
import ru.dovion.projectmanager.model.dto.TaskDto;
import ru.dovion.projectmanager.model.dto.TaskOutDto;

import java.util.List;

public interface UserService {

    TaskOutDto createTask(TaskDto task);


    TaskOutDto updateTaskStatus(Long id, Status status);


    void deleteTask(Long id);


    List<TaskOutDto> getAllTasks();


    List<ProjectOutDto> getAllProjects();
}
