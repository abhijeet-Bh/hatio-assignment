package com.hatio.todo.models;

import jakarta.persistence.*;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity // Marks the class as a JPA entity
@Table(name = "projects") // Defines the table name
public class ProjectModel {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "project")
    private List<TodoModel> todos = new ArrayList<>(); // Initialize the todos list

    // Constructor
    public ProjectModel(String title) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.createdDate = LocalDateTime.now();
    }

    // Default constructor (required by JPA)
    public ProjectModel() {
        this.todos = new ArrayList<>(); // Initialize in the default constructor as well
    }

    // Getters and Setters
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

    public List<TodoModel> getTodos() {
        return todos;
    }

    public void setTodos(List<TodoModel> todos) {
        this.todos = todos;
    }
}