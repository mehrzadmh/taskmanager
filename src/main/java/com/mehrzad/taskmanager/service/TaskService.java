package com.mehrzad.taskmanager.service;

import com.mehrzad.taskmanager.dto.request.create.TaskCreateRequest;
import com.mehrzad.taskmanager.dto.request.update.TaskUpdateRequest;
import com.mehrzad.taskmanager.dto.response.TaskResponse;
import com.mehrzad.taskmanager.entity.Task;
import com.mehrzad.taskmanager.entity.User;
import com.mehrzad.taskmanager.exception.DuplicateTitleException;
import com.mehrzad.taskmanager.exception.TaskNotFoundException;
import com.mehrzad.taskmanager.mapper.TaskMapper;
import com.mehrzad.taskmanager.repository.TaskRepository;
import com.mehrzad.taskmanager.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.mehrzad.taskmanager.exception.UserNotFoundException;

import java.util.List;

@Service
public class TaskService {

    private final TaskMapper taskMapper;
    private  final TaskRepository taskRepository;
    private final  UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper, UserRepository userRepository) {
        this.taskMapper = taskMapper;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }


    public TaskResponse saveTask(TaskCreateRequest request){

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(UserNotFoundException::new);

        if(taskRepository.existsByTitleAndUserId(request.getTitle(), request.getUserId())){
            throw new DuplicateTitleException();
        }

        Task task = taskMapper.toEntity(request);

        task.setUser(user);

        return taskMapper.toResponse(taskRepository.save(task));
    }


    public List<TaskResponse> getAllTasks() {

        return taskMapper.toResponseList(taskRepository.findAll());
    }

    public TaskResponse getTaskById(Long id) {

        Task task = taskRepository.findById(id)
                .orElseThrow(TaskNotFoundException::new);
        return  taskMapper.toResponse(task);

    }

    public void deleteTask(Long id) {

        Task task = taskRepository.findById(id)
                .orElseThrow(TaskNotFoundException::new);
        taskRepository.delete(task);
    }




    public TaskResponse updateTask(Long id, TaskUpdateRequest request ){

        Task task = taskRepository.findById(id)
                .orElseThrow(TaskNotFoundException::new);

        if(taskRepository.existsByTitleAndUserIdAndIdNot(request.getTitle(), task.getUser().getId(), task.getId())){
            throw new DuplicateTitleException();
        }

        taskMapper.updateEntity(task, request);
        return taskMapper.toResponse(taskRepository.save(task));

    }
}
