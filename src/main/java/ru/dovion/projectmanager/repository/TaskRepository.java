package ru.dovion.projectmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dovion.projectmanager.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
