package com.mehrzad.taskmanager.mapper;

import com.mehrzad.taskmanager.dto.request.create.UserCreateRequest;
import com.mehrzad.taskmanager.dto.response.UserResponse;
import com.mehrzad.taskmanager.dto.request.update.UserUpdateRequest;
import com.mehrzad.taskmanager.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public final class UserMapper {

    public  User toEntity(UserCreateRequest request) {

        User user = new User();

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setFatherName(request.getFatherName());

        return user;
    }

    public  UserResponse toResponse(User user) {

        UserResponse response = new UserResponse();

        response.setId(user.getId());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setFatherName(user.getFatherName());

        return response;
    }


    public  List<UserResponse> toResponseList(List<User> users) {

        List<UserResponse> responses = new ArrayList<>();

        for (User user : users) {
            responses.add(toResponse(user));
        }

        return responses;
    }


    public  void updateEntity(
            User user,
            UserUpdateRequest request){

        user.setFirstName(request.getFirstName());

        user.setLastName(request.getLastName());

        user.setFatherName(request.getFatherName());

        user.setEmail(request.getEmail());

    }


}