package com.mehrzad.taskmanager.repository;

import com.mehrzad.taskmanager.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    boolean existsByTitleAndUserId(String title, Long userId);

    boolean existsByTitleAndUserIdAndIdNot(
            String title,
            Long userId,
            Long id
    );
}