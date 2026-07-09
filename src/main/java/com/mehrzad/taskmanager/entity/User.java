package com.mehrzad.taskmanager.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "USERS")
@Data
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column
    private String fatherName;

    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "user")
    private List<Task> tasks;
}