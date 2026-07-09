package com.mehrzad.taskmanager.config;

import com.mehrzad.taskmanager.entity.User;
import com.mehrzad.taskmanager.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TestRunner implements CommandLineRunner {

    private final UserRepository userRepository;

    public TestRunner(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {

       /* User user = userRepository.findById(3952L)
                .orElseThrow();

        System.out.println("========== USER LOADED ==========");

        System.out.println(user.getFirstName());

        System.out.println("========== BEFORE TASKS ==========");

        System.out.println(user.getTasks().size());

        System.out.println("========== AFTER TASKS ==========");*/

    }
}