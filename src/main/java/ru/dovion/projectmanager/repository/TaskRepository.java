package ru.dovion.projectmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dovion.projectmanager.model.Task;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAllByUserId(Long userId);
}
