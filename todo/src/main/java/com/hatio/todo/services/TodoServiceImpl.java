package com.hatio.todo.services;

import com.hatio.todo.models.ProjectModel;
import com.hatio.todo.models.TodoModel;
import com.hatio.todo.repositories.ProjectRepository;
import com.hatio.todo.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public TodoModel createTodo(UUID projectId, String description) {
        ProjectModel project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        TodoModel todo = new TodoModel(description);
        project.addTodo(todo);
        projectRepository.save(project);

        return todo;
    }

    @Override
    public TodoModel updateTodo(UUID projectId, UUID todoId, String description, String status) {
        ProjectModel project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        TodoModel todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new RuntimeException("Todo not found"));

        todo.setDescription(description);
        todo.setStatus(status);

        todoRepository.save(todo);

        return todo;
    }

    @Override
    public void deleteTodo(UUID projectId, UUID todoId) {
        ProjectModel project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        TodoModel todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new RuntimeException("Todo not found"));

        project.getTodos().remove(todo);
        todoRepository.delete(todo);
    }

    @Override
    public TodoModel markTodoAsComplete(UUID projectId, UUID todoId) {
        TodoModel todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new RuntimeException("Todo not found"));

        todo.setStatus("complete");
        todoRepository.save(todo);

        return todo;
    }

    @Override
    public TodoModel markTodoAsPending(UUID projectId, UUID todoId) {
        TodoModel todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new RuntimeException("Todo not found"));

        todo.setStatus("pending");
        todoRepository.save(todo);

        return todo;
    }

    @Override
    public List<TodoModel> getAllTodosByProject(UUID projectId) {
        ProjectModel project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        return project.getTodos();
    }
}
