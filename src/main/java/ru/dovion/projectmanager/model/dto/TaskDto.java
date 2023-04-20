package ru.dovion.projectmanager.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.dovion.projectmanager.model.TaskType;

import java.io.Serializable;

@Data
public class TaskDto implements Serializable {

    @NotNull(message = "Тип задачи не может отсутствовать")
    private final TaskType taskType;
    @NotBlank(message = "Название задачи не может отсутствовать")
    @NotNull(message = "Название задачи не может быть пустым")
    @Schema(defaultValue = "New task")
    private final String title;
    @NotBlank(message = "Описание задачи не может быть пустым")
    @NotNull(message = "Описание задачи не может отсутствовать")
    @Schema(defaultValue = "New task description")
    private final String description;
    @NotNull(message = "Идентификатор проекта для задачи не может отсутствовать")
    @Schema(defaultValue = "1")
    private final Long projectId;
}