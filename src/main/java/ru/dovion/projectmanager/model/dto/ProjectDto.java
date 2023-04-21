package ru.dovion.projectmanager.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class ProjectDto implements Serializable {

    @NotNull(message = "Название проекта не может отсутствовать")
    @NotBlank(message = "Название проекта не может быть пустым")
    @Schema(defaultValue = "New project")
    private final String title;
    @Schema(defaultValue = "1", description = "Следует удалить данное свойство, если объект не является подпроектом")
    private final Long parent;
}