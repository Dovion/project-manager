package ru.dovion.projectmanager.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.dovion.projectmanager.exception.BusinessException;
import ru.dovion.projectmanager.model.Project;
import ru.dovion.projectmanager.model.dto.ProjectDto;
import ru.dovion.projectmanager.model.dto.ProjectOutDto;
import ru.dovion.projectmanager.model.dto.TaskDto;
import ru.dovion.projectmanager.model.dto.TaskOutDto;
import ru.dovion.projectmanager.model.mapper.ProjectMapper;
import ru.dovion.projectmanager.model.mapper.TaskMapper;
import ru.dovion.projectmanager.repository.ProjectRepository;
import ru.dovion.projectmanager.repository.TaskRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final ProjectRepository projectRepository;

    private final TaskRepository taskRepository;

    @Override
    public TaskOutDto updateTask(TaskDto task, Long id) {
        log.info("Обновление задачи");
        var foundedTask = taskRepository.findById(id)
                                        .orElseThrow(() -> new EntityNotFoundException(
                                                "Передан неверный идентификатор задачи"));
        var foundedProject = projectRepository.findById(task.getProjectId())
                                              .orElseThrow(() -> new EntityNotFoundException(
                                                      "Передан неверный идентификатор проекта"));
        foundedTask.setTaskType(task.getTaskType());
        foundedTask.setTitle(task.getTitle());
        foundedTask.setDescription(task.getDescription());
        foundedTask.setProject(foundedProject);
        return TaskMapper.toOutDto(taskRepository.saveAndFlush(foundedTask));
    }

    @Override
    public void deleteTask(Long id) {
        log.info("Удаление задачи");
        var foundedTask = taskRepository.findById(id)
                                        .orElseThrow(() -> new EntityNotFoundException(
                                                "Передан неверный идентификатор задачи"));
        taskRepository.deleteById(id);
    }

    @Override
    public ProjectOutDto createProject(ProjectDto project) {
        log.info("Создание нового проекта");
        Project parent = null;
        if (project.getParent() != null) {
            parent = projectRepository.findById(project.getParent())
                                      .orElseThrow(() -> new EntityNotFoundException(
                                              "Передан неверный идентификатор родительского проекта"));
        }
        Project newProject = ProjectMapper.fromDto(project);
        newProject.setId(null);
        newProject.setParent(parent);
        return ProjectMapper.toOutDto(projectRepository.saveAndFlush(newProject));
    }

    @Override
    public ProjectOutDto updateProject(ProjectDto project, Long id) throws BusinessException {
        log.info("Обновление проекта");
        var foundedProject = projectRepository.findById(id)
                                              .orElseThrow(() -> new EntityNotFoundException(
                                                      "Передан неверный идентификатор проекта"));
        Project foundedParent = null;
        if (project.getParent() != null) {
            foundedParent = projectRepository.findById(project.getParent())
                                             .orElseThrow(() -> new EntityNotFoundException(
                                                     "Передан неверный идентификатор родительского проекта"));
        }

        if (foundedParent != null && foundedParent.getParent() != null) {
            if (foundedParent.getId().equals(id)) {
                throw new BusinessException("Проект не может быть родителем для самого себя");
            }
            List<Long> idList = new ArrayList<>();
            Project parent = foundedParent.getParent();
            while (parent != null) {
                idList.add(parent.getId());
                parent = parent.getParent();
            }
            if (idList.contains(foundedProject.getId())) {
                throw new BusinessException("Подпроект не может быть родительским для самого родителя");
            }
        }
        foundedProject.setTitle(project.getTitle());
        foundedProject.setParent(foundedParent);
        return ProjectMapper.toOutDto(projectRepository.saveAndFlush(foundedProject));
    }

    @Override
    public void deleteProject(Long id) {
        log.info("Удаление проекта");
        var foundedProject = projectRepository.findById(id)
                                              .orElseThrow(() -> new EntityNotFoundException(
                                                      "Передан неверный идентификатор проекта"));
        projectRepository.deleteById(id);
    }

    @Override
    public List<TaskOutDto> getAllTasks() {
        log.info("Получение всех задач");
        return taskRepository.findAll().stream().map(task -> TaskMapper.toOutDto(task)).collect(Collectors.toList());
    }
}
