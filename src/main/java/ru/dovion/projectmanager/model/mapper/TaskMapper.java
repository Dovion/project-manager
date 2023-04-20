package ru.dovion.projectmanager.model.mapper;

import org.modelmapper.ModelMapper;
import ru.dovion.projectmanager.model.Task;
import ru.dovion.projectmanager.model.dto.TaskDto;
import ru.dovion.projectmanager.model.dto.TaskOutDto;

public class TaskMapper {

    private final static ModelMapper modelMapper = new ModelMapper();

    public static Task fromDto(TaskDto taskDto) {
        return modelMapper.map(taskDto, Task.class);
    }

    public static TaskOutDto toOutDto(Task task) {
        return new TaskOutDto(task.getId(), task.getTaskType(), task.getTitle(), task.getDescription(),
                task.getStatus(), task.getProject().getId(), task.getStartDate(), task.getUpdateDate());
    }
}
