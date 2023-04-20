package ru.dovion.projectmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dovion.projectmanager.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
