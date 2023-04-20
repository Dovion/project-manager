package ru.dovion.projectmanager.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.dovion.projectmanager.model.Status;
import ru.dovion.projectmanager.model.Task;
import ru.dovion.projectmanager.model.dto.ProjectOutDto;
import ru.dovion.projectmanager.model.dto.TaskDto;
import ru.dovion.projectmanager.model.dto.TaskOutDto;
import ru.dovion.projectmanager.model.mapper.ProjectMapper;
import ru.dovion.projectmanager.model.mapper.TaskMapper;
import ru.dovion.projectmanager.repository.ProjectRepository;
import ru.dovion.projectmanager.repository.TaskRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final ProjectRepository projectRepository;

    private final TaskRepository taskRepository;

    @Override
    public TaskOutDto createTask(TaskDto task) {
        log.info("Создание новой задачи");
        var foundedProject = projectRepository.findById(task.getProjectId())
                                              .orElseThrow(() -> new EntityNotFoundException(
                                                      "Передан неверный идентификатор проекта"));
        Task newTask = TaskMapper.fromDto(task);
        newTask.setProject(foundedProject);
        newTask.setStatus(Status.NEW);
        return TaskMapper.toOutDto(taskRepository.saveAndFlush(newTask));
    }

    @Override
    public TaskOutDto updateTaskStatus(Long id, Status status) {
        log.info("Обновление статуса задачи");
        var foundedTask = taskRepository.findById(id)
                                        .orElseThrow(() -> new EntityNotFoundException(
                                                "Передан неверный идентификатор задачи"));
        foundedTask.setStatus(status);
        return TaskMapper.toOutDto(taskRepository.saveAndFlush(foundedTask));
    }

    @Override
    public void deleteTask(Long id) {
        log.info("Удаление задачи");
        taskRepository.deleteById(id);
    }

    @Override
    public List<TaskOutDto> getAllTasks() {
        log.info("Получение всех задач");
        return taskRepository.findAll().stream().map(task -> TaskMapper.toOutDto(task)).collect(Collectors.toList());
    }

    @Override
    public List<ProjectOutDto> getAllProjects() {
        log.info("Получение всех проектов");
        return projectRepository.findAll()
                                .stream()
                                .map(project -> ProjectMapper.toOutDto(project))
                                .collect(Collectors.toList());
    }
}
