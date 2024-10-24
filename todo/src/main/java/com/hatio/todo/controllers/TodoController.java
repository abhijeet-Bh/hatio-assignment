package com.hatio.todo.controllers;

import com.hatio.todo.dtos.TodoRequest;
import com.hatio.todo.models.TodoModel;
import com.hatio.todo.services.TodoService;
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
@RequestMapping("/api/v1/projects/{projectId}/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @PostMapping
    public ResponseEntity<ApiResponse> addTodo(@PathVariable UUID projectId, @RequestBody TodoRequest todoRequest) {
        try {
            TodoModel todo = todoService.createTodo(projectId, todoRequest.getDescription());
            return new ResponseEntity<>(new SuccessResponse<TodoModel>(true, 200, todo), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(new ErrorResponse(503, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{todoId}")
    public ResponseEntity<ApiResponse> updateTodo(@PathVariable UUID projectId,
                                            @PathVariable UUID todoId,
                                            @RequestBody TodoRequest todoRequest) {
        try {
            TodoModel todo = todoService.updateTodo(projectId, todoId, todoRequest.getDescription(), todoRequest.getStatus());
            return new ResponseEntity<>(new SuccessResponse<TodoModel>(true, 201, todo), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(new ErrorResponse(503, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<ApiResponse> deleteTodo(@PathVariable UUID projectId, @PathVariable UUID todoId) {
        try {
            todoService.deleteTodo(projectId, todoId);
            return ResponseEntity.ok(new SuccessResponse<String>(true, 200, "Todo Deleted Successfully!"));
        }catch (Exception e){
            return new ResponseEntity<>(new ErrorResponse(503, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{todoId}/complete")
    public ResponseEntity<ApiResponse> markTodoAsComplete(@PathVariable UUID projectId, @PathVariable UUID todoId) {
        try {
            TodoModel todo = todoService.markTodoAsComplete(projectId, todoId);
            return ResponseEntity.ok(new SuccessResponse<TodoModel>(true, 201, todo));
        }catch (Exception e){
            return new ResponseEntity<>(new ErrorResponse(503, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{todoId}/pending")
    public ResponseEntity<ApiResponse> markTodoAsPending(@PathVariable UUID projectId, @PathVariable UUID todoId) {
        try {
            TodoModel todo = todoService.markTodoAsPending(projectId, todoId);
            return ResponseEntity.ok(new SuccessResponse<TodoModel>(true, 201, todo));
        }catch (Exception e){
            return new ResponseEntity<>(new ErrorResponse(503, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllTodos(@PathVariable UUID projectId) {
        try {
            List<TodoModel> todos = todoService.getAllTodosByProject(projectId);
            return ResponseEntity.ok(new SuccessResponse<List<TodoModel>>(true, 200, todos));
        }catch (Exception e){
            return new ResponseEntity<>(new ErrorResponse(503, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
