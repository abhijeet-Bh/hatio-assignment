package com.hatio.todo;

import com.hatio.todo.utils.ApiResponse;
import com.hatio.todo.utils.SuccessResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {
    @GetMapping("api/v1/healthz")
    public ResponseEntity<ApiResponse> healthCheck(){
        return ResponseEntity.ok(new SuccessResponse<>(
                true,
                200,
                "Todo App is working fine!"
        ));
    }
}
