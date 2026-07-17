package com.mehrzad.taskmanager.mapper;

import com.mehrzad.taskmanager.dto.request.create.TaskCreateRequest;
import com.mehrzad.taskmanager.dto.request.update.TaskUpdateRequest;
import com.mehrzad.taskmanager.dto.response.TaskResponse;
import com.mehrzad.taskmanager.dto.response.UserResponse;
import com.mehrzad.taskmanager.entity.Task;
import com.mehrzad.taskmanager.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskMapper {

    public  Task toEntity(TaskCreateRequest request) {

        Task task = new Task();

        task.setDescription(request.getDescription());
        task.setTitle(request.getTitle());
        task.setPriority(request.getPriority());

    return task;

    }

    public  TaskResponse toResponse(Task task) {

        TaskResponse response = new TaskResponse();

        response.setId(task.getId());
        response.setDescription(task.getDescription());
        response.setTitle(task.getTitle());
        response.setPriority(task.getPriority());
        response.setStatus(task.getStatus());
        response.setCreatedAt(task.getCreatedAt());
        response.setUpdatedAt(task.getUpdatedAt());
        response.setUserId(task.getUser().getId());


        return response;
    }

    public List<TaskResponse> toResponseList(List<Task> tasks) {

        List<TaskResponse> responses = new ArrayList<>();


        for (Task task : tasks) {
            responses.add(toResponse(task));
        }

        return responses;


    }


    public  void updateEntity(
            Task task,
            TaskUpdateRequest request){

        task.setDescription(request.getDescription());
        task.setTitle(request.getTitle());
        task.setPriority(request.getPriority());
        task.setStatus(request.getStatus());

    }

}
