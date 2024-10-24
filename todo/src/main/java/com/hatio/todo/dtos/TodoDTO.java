package com.hatio.todo.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public class TodoDTO {

    private UUID id;
    private String description;
    private String status; // Could be "pending" or "complete"
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    // Constructors, Getters, and Setters

    public TodoDTO(UUID id, String description, String status, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

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
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
}
