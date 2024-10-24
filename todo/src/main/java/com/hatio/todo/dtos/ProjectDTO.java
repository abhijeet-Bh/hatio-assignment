package com.hatio.todo.dtos;

import com.hatio.todo.models.ProjectModel;

import java.util.UUID;
import java.time.LocalDateTime;
import java.util.List;

public class ProjectDTO {

    private UUID id;
    private String title;
    private LocalDateTime createdDate;
    private List<TodoDTO> todos;

    // Constructors, Getters, and Setters

    public ProjectDTO(UUID id, String title, LocalDateTime createdDate, List<TodoDTO> todos) {
        this.id = id;
        this.title = title;
        this.createdDate = createdDate;
        this.todos = todos;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public List<TodoDTO> getTodos() {
        return todos;
    }

    public void setTodos(List<TodoDTO> todos) {
        this.todos = todos;
    }
}
