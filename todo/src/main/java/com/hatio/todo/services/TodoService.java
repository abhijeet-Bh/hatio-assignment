package com.hatio.todo.services;

import com.hatio.todo.models.TodoModel;

import java.util.List;
import java.util.UUID;

public interface TodoService {

    TodoModel createTodo(UUID projectId, String description);

    TodoModel updateTodo(UUID projectId, UUID todoId, String description, String status);

    void deleteTodo(UUID projectId, UUID todoId);

    TodoModel markTodoAsComplete(UUID projectId, UUID todoId);

    TodoModel markTodoAsPending(UUID projectId, UUID todoId);

    List<TodoModel> getAllTodosByProject(UUID projectId);
}
