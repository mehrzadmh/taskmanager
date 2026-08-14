package com.mehrzad.taskmanager.repository;

import com.mehrzad.taskmanager.entity.Task;
import com.mehrzad.taskmanager.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>, JpaSpecificationExecutor<Task> {

    boolean existsByTitleAndUserId(String title, Long userId);

    boolean existsByTitleAndUserIdAndIdNot(
            String title,
            Long userId,
            Long id
    );


    @Query("""
            SELECT COUNT(t) > 0
            FROM Task t
            WHERE t.user.id = :userId
            AND t.status = :status
            """)
    Boolean findTasksByUserAndStatus(Long userId, TaskStatus status);
}