package com.hatio.todo.dtos;

public class CreateProjectDTO {

    private String title;

    // Constructors, Getters, and Setters
    public CreateProjectDTO() {}

    public CreateProjectDTO(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
