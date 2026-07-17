package com.mehrzad.taskmanager.entity;

import com.mehrzad.taskmanager.enums.TaskPriority;
import com.mehrzad.taskmanager.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

import java.util.Date;

@Entity
@Table(name = "TASKS")
@Data
public class Task {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;
    @Enumerated(EnumType.STRING)
    private TaskPriority priority;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

}