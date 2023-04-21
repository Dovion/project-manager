package ru.dovion.projectmanager.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.dovion.projectmanager.model.Status;
import ru.dovion.projectmanager.model.TaskType;
import ru.dovion.projectmanager.model.User;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskOutDto {

    private Long id;

    private TaskType taskType;

    private String title;

    private String description;

    private Status status;

    private Long project;

    private User user;

    private LocalDateTime startDate;

    private LocalDateTime updateDate;
}
