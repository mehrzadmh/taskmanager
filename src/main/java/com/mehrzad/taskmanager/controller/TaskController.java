package com.mehrzad.taskmanager.controller;

import com.mehrzad.taskmanager.dto.request.create.TaskCreateRequest;
import com.mehrzad.taskmanager.dto.request.update.TaskUpdateRequest;
import com.mehrzad.taskmanager.dto.response.TaskResponse;
import com.mehrzad.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(
            @Valid @RequestBody TaskCreateRequest request){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(taskService.saveTask(request));
    }

    @GetMapping("/all")
    public ResponseEntity<List<TaskResponse>> getAllTasks(){
        return ResponseEntity.ok(taskService.getAllTasks());
    }


    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable Long id){

        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(
            @PathVariable Long id,
            @Valid @RequestBody TaskUpdateRequest request){
        return ResponseEntity.ok(taskService.updateTask(id,request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

}
