package com.mehrzad.taskmanager.service;

import com.mehrzad.taskmanager.dto.request.create.TaskCreateRequest;
import com.mehrzad.taskmanager.dto.request.update.TaskUpdateRequest;
import com.mehrzad.taskmanager.dto.response.TaskResponse;
import com.mehrzad.taskmanager.entity.Task;
import com.mehrzad.taskmanager.entity.User;
import com.mehrzad.taskmanager.enums.TaskPriority;
import com.mehrzad.taskmanager.enums.TaskStatus;
import com.mehrzad.taskmanager.exception.AccessDeniedException;
import com.mehrzad.taskmanager.exception.DuplicateTitleException;
import com.mehrzad.taskmanager.exception.TaskNotFoundException;
import com.mehrzad.taskmanager.mapper.TaskMapper;
import com.mehrzad.taskmanager.repository.TaskRepository;
import com.mehrzad.taskmanager.security.SecurityUser;
import com.mehrzad.taskmanager.security.SecurityUtils;
import com.mehrzad.taskmanager.specification.TaskSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {

    private final TaskMapper taskMapper;
    private  final TaskRepository taskRepository;


    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
        this.taskRepository = taskRepository;
    }


    public TaskResponse saveTask(TaskCreateRequest request) {

        SecurityUser securityUser = SecurityUtils.getCurrentUser();

        User user = securityUser.getUser();

        if (taskRepository.existsByTitleAndUserId(
                request.getTitle(),
                user.getId())) {

            throw new DuplicateTitleException();
        }

        Task task = taskMapper.toEntity(request);

        task.setCreatedAt(LocalDateTime.now());
        task.setStatus(TaskStatus.TODO);
        task.setUser(user);

        return taskMapper.toResponse(taskRepository.save(task));
    }


    public Page<TaskResponse> getAllTasks(
            int page,
            int size,
            String sortBy,
            String direction
    ) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable =
                PageRequest.of(page, size, sort);

        Page<Task> tasks =
                taskRepository.findAll(pageable);

        return tasks.map(taskMapper::toResponse);
    }

    public TaskResponse getTaskById(Long id) {

        Task task = taskRepository.findById(id)
                .orElseThrow(TaskNotFoundException::new);
        validateTaskOwner(task);
        return  taskMapper.toResponse(task);

    }

    public void deleteTask(Long id) {

        Task task = taskRepository.findById(id)
                .orElseThrow(TaskNotFoundException::new);
        validateTaskOwner(task);
        taskRepository.delete(task);
    }

    private void validateTaskOwner(Task task) {

        SecurityUser currentUser = SecurityUtils.getCurrentUser();

        if (!task.getUser().getId().equals(currentUser.getUser().getId())) {
            throw new AccessDeniedException();
        }
    }



    @Transactional
    public TaskResponse updateTask(Long id, TaskUpdateRequest request ){

        Task task = taskRepository.findById(id)
                .orElseThrow(TaskNotFoundException::new);
        validateTaskOwner(task);
        if(taskRepository.existsByTitleAndUserIdAndIdNot(request.getTitle(), task.getUser().getId(), task.getId())){
            throw new DuplicateTitleException();
        }

        taskMapper.updateEntity(task, request);
        return taskMapper.toResponse(taskRepository.save(task));

    }

    public List<TaskResponse> searchTasks(
            String title,
            TaskStatus status,
            TaskPriority priority) {

        Specification<Task> specification =
                         TaskSpecification.hasTitle(title)
                        .and(TaskSpecification.hasStatus(status))
                        .and(TaskSpecification.hasPriority(priority));

        List<Task> tasks = taskRepository.findAll(specification);
        return taskMapper.toResponseList(tasks);
    }


}
