package com.hatio.todo.repositories;

import com.hatio.todo.models.ProjectModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectModel, UUID> {
    // Additional query methods can be added here if needed
}
