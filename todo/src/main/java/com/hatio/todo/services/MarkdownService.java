package com.hatio.todo.services;

import com.hatio.todo.models.ProjectModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarkdownService {

    public String generateProjectSummary(ProjectModel project) {
        StringBuilder markdown = new StringBuilder();

        // Project Title
        markdown.append("# ").append(project.getTitle()).append("\n\n");

        // Summary: Number of completed todos vs total todos
        long completedTodos = project.getTodos().stream().filter(todo -> todo.getStatus().equals("complete")).count();
        markdown.append("**Summary**: ")
                .append(completedTodos).append(" / ")
                .append(project.getTodos().size()).append(" completed.\n\n");

        // Pending Todos Section
        markdown.append("## Pending Todos\n");
        project.getTodos().stream()
                .filter(todo -> todo.getStatus().equals("pending"))
                .forEach(todo -> markdown.append("- [ ] ").append(todo.getDescription()).append("\n"));

        // Completed Todos Section
        markdown.append("\n## Completed Todos\n");
        project.getTodos().stream()
                .filter(todo -> todo.getStatus().equals("complete"))
                .forEach(todo -> markdown.append("- [x] ").append(todo.getDescription()).append("\n"));

        return markdown.toString();
    }
}
