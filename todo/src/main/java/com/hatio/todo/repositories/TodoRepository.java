package com.hatio.todo.repositories;

import com.hatio.todo.models.TodoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface TodoRepository extends JpaRepository<TodoModel, UUID> { }
