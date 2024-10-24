package com.hatio.todo.services;

import com.hatio.todo.dtos.CreateProjectDTO;
import com.hatio.todo.dtos.ProjectDTO;

import java.util.List;
import java.util.UUID;

public interface ProjectService {

    List<ProjectDTO> getAllProjects();

    ProjectDTO getProjectById(UUID projectId);

    ProjectDTO createProject(CreateProjectDTO projectDTO);

    ProjectDTO updateProject(UUID projectId, CreateProjectDTO projectDTO);

    void deleteProject(UUID projectId);
}
