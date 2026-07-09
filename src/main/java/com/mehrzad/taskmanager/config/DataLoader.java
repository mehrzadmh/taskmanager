package com.mehrzad.taskmanager.config;

import com.mehrzad.taskmanager.entity.User;
import com.mehrzad.taskmanager.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;

    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {

       /*User user = new User();
        user.setFirstName("Mehrzad2");
        user.setLastName("Hajkhalil2");
        user.setEmail("mehrzad4@test.com");


        userRepository.save(user);

        System.out.println("User Saved Successfully");*/

        /*
        userRepository.deleteById(2L);

        System.out.println("User Deleted");*/
    }
}