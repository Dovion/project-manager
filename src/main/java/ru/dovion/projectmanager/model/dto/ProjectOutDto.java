package ru.dovion.projectmanager.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProjectOutDto {

    private Long id;

    private String title;

    private List<Long> subprojects;

    private List<TaskOutDto> tasks;

    private Long parent;

    private LocalDateTime startDate;

    private LocalDateTime updateDate;
}
