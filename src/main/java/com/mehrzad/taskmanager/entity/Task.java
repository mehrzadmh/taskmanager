package com.mehrzad.taskmanager.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import java.util.Date;

@Entity
@Table(name = "TASKS")
public class Task {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    private String priority;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

}