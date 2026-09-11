package com.mehrzad.taskmanager.controller;

import com.mehrzad.taskmanager.dto.request.create.TaskCreateRequest;
import com.mehrzad.taskmanager.dto.request.update.TaskUpdateRequest;
import com.mehrzad.taskmanager.dto.response.TaskResponse;
import com.mehrzad.taskmanager.enums.TaskPriority;
import com.mehrzad.taskmanager.enums.TaskStatus;
import com.mehrzad.taskmanager.service.TaskService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import io.swagger.v3.oas.annotations.Operation;

@Tag(name = "Task Management", description = "Task Management APIs")
@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    @Operation(summary = "Create new task")
    @PostMapping
    public ResponseEntity<TaskResponse> createTask(
            @Valid @RequestBody TaskCreateRequest request){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(taskService.saveTask(request));
    }
    @Operation(summary = "Get All Tasks")
    @GetMapping("/all")
    public ResponseEntity<Page<TaskResponse>> getAllTasks(

            @RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "10") int size,

            @RequestParam(defaultValue = "id") String sortBy,

            @RequestParam(defaultValue = "asc") String direction

    ) {

        return ResponseEntity.ok(
                taskService.getAllTasks(page, size, sortBy, direction)
        );
    }

    @Operation(summary = "Get Task By Id")
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable Long id){

        return ResponseEntity.ok(taskService.getTaskById(id));
    }
    @Operation(summary = "Update Task By Id")
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(
            @PathVariable Long id,
            @Valid @RequestBody TaskUpdateRequest request){
        return ResponseEntity.ok(taskService.updateTask(id,request));
    }
    @Operation(summary = "Delete Task By Id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
    @Operation(summary = "Search Task By Params")
    @GetMapping("/search")
    public ResponseEntity<List<TaskResponse>> searchTasks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) TaskStatus status,
            @RequestParam(required = false) TaskPriority priority) {

        return ResponseEntity.ok(
                taskService.searchTasks(title, status, priority)
        );
    }

}
