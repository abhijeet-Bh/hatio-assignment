package com.hatio.todo.models;

import com.hatio.todo.models.ProjectModel;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity // Marks the class as a JPA entity
@Table(name = "todos") // Defines the table name
public class TodoModel {

    @Id // Marks this field as the primary key
    @GeneratedValue // Automatically generates a unique identifier (UUID in this case)
    private UUID id;

    @Column(nullable = false) // Ensures the description is not null
    private String description;

    @Column(nullable = false) // Ensures the status is not null
    private String status;

    @Column(name = "created_date", nullable = false) // Maps to "created_date" in the DB
    private LocalDateTime createdDate;

    @Column(name = "updated_date", nullable = false) // Maps to "updated_date" in the DB
    private LocalDateTime updatedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false) // Foreign key mapping to Project
    private ProjectModel project; // Reference to the project to which this todo belongs

    // Constructor
    public TodoModel(String description, ProjectModel project) {
        this.id = UUID.randomUUID(); // Generate unique UUID
        this.description = description;
        this.status = "pending"; // Default status to pending
        this.createdDate = LocalDateTime.now(); // Set creation date to current time
        this.updatedDate = LocalDateTime.now(); // Set updated date to current time
        this.project = project;
    }

    // Default constructor (required by JPA)
    public TodoModel() {
    }

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        this.updatedDate = LocalDateTime.now(); // Update the updated date whenever description changes
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
        this.updatedDate = LocalDateTime.now(); // Update the updated date whenever status changes
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public ProjectModel getProject() {
        return project;
    }

    public void setProject(ProjectModel project) {
        this.project = project;
    }
}