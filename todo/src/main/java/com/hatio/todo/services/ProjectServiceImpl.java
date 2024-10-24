package com.hatio.todo.services;

import com.hatio.todo.dtos.CreateProjectDTO;
import com.hatio.todo.dtos.ProjectDTO;
import com.hatio.todo.dtos.TodoDTO;
import com.hatio.todo.models.ProjectModel;
import com.hatio.todo.models.TodoModel;
import com.hatio.todo.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public List<ProjectDTO> getAllProjects() {
        return projectRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProjectDTO getProjectById(UUID projectId) {
        ProjectModel project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        return convertToDTO(project);
    }

    @Override
    public ProjectDTO createProject(CreateProjectDTO projectDTO) {
        ProjectModel project = new ProjectModel(projectDTO.getTitle());
        ProjectModel savedProject = projectRepository.save(project);
        return convertToDTO(savedProject);
    }

    @Override
    public ProjectDTO updateProject(UUID projectId, CreateProjectDTO projectDTO) {
        ProjectModel project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        project.setTitle(projectDTO.getTitle());
        ProjectModel updatedProject = projectRepository.save(project);
        return convertToDTO(updatedProject);
    }

    @Override
    public void deleteProject(UUID projectId) {
        projectRepository.deleteById(projectId);
    }

    // Helper method to convert Project to ProjectDTO
    private ProjectDTO convertToDTO(ProjectModel project) {
        return new ProjectDTO(
                project.getId(),
                project.getTitle(),
                project.getCreatedDate(),
                project.getTodos().stream().map(this::convertTodoToDTO).collect(Collectors.toList())
        );
    }

    private TodoDTO convertTodoToDTO(TodoModel todo) {
        return new TodoDTO(todo.getId(), todo.getDescription(), todo.getStatus(), todo.getCreatedDate(), todo.getUpdatedDate());
    }
}
