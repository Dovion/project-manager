package ru.dovion.projectmanager.model.mapper;

import org.modelmapper.ModelMapper;
import ru.dovion.projectmanager.model.Project;
import ru.dovion.projectmanager.model.dto.ProjectDto;
import ru.dovion.projectmanager.model.dto.ProjectOutDto;

import java.util.List;
import java.util.stream.Collectors;

public class ProjectMapper {

    private final static ModelMapper modelMapper = new ModelMapper();

    public static Project fromDto(ProjectDto projectDto) {
        return modelMapper.map(projectDto, Project.class);
    }

    public static ProjectOutDto toOutDto(Project project) {
        if (project.getSubprojects() == null) {
            project.setSubprojects(List.of());
        }
        if (project.getTasks() == null) {
            project.setTasks(List.of());
        }
        return new ProjectOutDto(project.getId(), project.getTitle(),
                project.getSubprojects().stream().map(Project::getId).collect(Collectors.toList()),
                project.getTasks().stream().map(TaskMapper::toOutDto).toList(),
                project.getParent() == null ? null : project.getParent().getId(), project.getStartDate(),
                project.getUpdateDate());
    }
}
