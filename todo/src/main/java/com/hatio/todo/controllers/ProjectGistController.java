package com.hatio.todo.controllers;

import com.hatio.todo.models.ProjectModel;
import com.hatio.todo.repositories.ProjectRepository;
import com.hatio.todo.services.GistService;
import com.hatio.todo.services.MarkdownService;
import com.hatio.todo.utils.ApiResponse;
import com.hatio.todo.utils.ErrorResponse;
import com.hatio.todo.utils.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/projects")
public class ProjectGistController {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private GistService gistService;

    @Autowired
    private MarkdownService markdownService;

    @PostMapping("/{projectId}/export-gist")
    public ResponseEntity<ApiResponse> exportProjectAsGist(@PathVariable UUID projectId, @RequestParam String githubToken) {
        // Find the project
        ProjectModel project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        // Generate markdown content
        String markdownContent = markdownService.generateProjectSummary(project);

        // Create the gist
        try {
            String gistUrl = gistService.createSecretGist(markdownContent, project.getTitle(), githubToken);
            return ResponseEntity.ok(new SuccessResponse<String>(true, 201, gistUrl));
        } catch (IOException e) {
            return new ResponseEntity<>(new ErrorResponse(403, "Failed to create gist"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{projectId}/download-summary")
    public ResponseEntity<byte[]> downloadProjectSummary(@PathVariable UUID projectId) {
        // Find the project
        ProjectModel project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        // Generate markdown content
        String markdownContent = markdownService.generateProjectSummary(project);

        // Create the file and return as byte array
        try {
            Path path = Files.write(Paths.get(project.getTitle() + ".md"), markdownContent.getBytes());
            byte[] fileBytes = Files.readAllBytes(path);

            // Build response
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + project.getTitle() + ".md");
            return new ResponseEntity<>(fileBytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
