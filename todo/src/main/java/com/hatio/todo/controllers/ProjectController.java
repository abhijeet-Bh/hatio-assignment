package com.hatio.todo.controllers;

import com.hatio.todo.dtos.CreateProjectDTO;
import com.hatio.todo.dtos.ProjectDTO;
import com.hatio.todo.models.ProjectModel;
import com.hatio.todo.services.ProjectService;
import com.hatio.todo.utils.ApiResponse;
import com.hatio.todo.utils.ErrorResponse;
import com.hatio.todo.utils.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    // Create a new project
    @PostMapping
    public ResponseEntity<ApiResponse> createProject(@RequestBody CreateProjectDTO projectDTO) {
        try {
            ProjectDTO createdProject = projectService.createProject(projectDTO);
            return ResponseEntity.ok(new SuccessResponse<>(true, 201, createdProject));
        }catch (Exception e){
            return new ResponseEntity<>(new ErrorResponse(503, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get all projects
    @GetMapping
    public ResponseEntity<ApiResponse> getAllProjects() {
        try {
            List<ProjectDTO> projects = projectService.getAllProjects();
            return ResponseEntity.ok(new SuccessResponse<List<ProjectDTO>>(true, 200, projects));
        }catch (Exception e){
            return new ResponseEntity<>(new ErrorResponse(503, "Something went wrong"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get a project by ID
    @GetMapping("/{projectId}")
    public ResponseEntity<ApiResponse> getProjectById(@PathVariable UUID projectId) {
        try{
            ProjectDTO project = projectService.getProjectById(projectId);
            return ResponseEntity.ok(new SuccessResponse<ProjectDTO>(true, 200, project));
        }catch (Exception e){
            return new ResponseEntity<>(new ErrorResponse(404, "Project Not Fount"), HttpStatus.NOT_FOUND);
        }
    }

    // Update a project by ID
    @PutMapping("/{projectId}")
    public ResponseEntity<ApiResponse> updateProject(@PathVariable UUID projectId, @RequestBody CreateProjectDTO projectDTO) {
        try {
            ProjectDTO updatedProject = projectService.updateProject(projectId, projectDTO);
            return ResponseEntity.ok(new SuccessResponse<ProjectDTO>(true, 201, updatedProject));
        }catch (Exception e){
            return new ResponseEntity<>(new ErrorResponse(503, "Cannot Update Project!"),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete a project by ID
    @DeleteMapping("/{projectId}")
    public ResponseEntity<ApiResponse> deleteProject(@PathVariable UUID projectId) {
        try {
            projectService.deleteProject(projectId);
            return new ResponseEntity<>(new SuccessResponse<>(true, 402, "Deleted Project Successfully!"), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ErrorResponse(503, "Failed to delete Project!"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
