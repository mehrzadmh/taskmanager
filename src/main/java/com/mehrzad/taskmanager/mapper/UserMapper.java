package com.mehrzad.taskmanager.mapper;

import com.mehrzad.taskmanager.dto.UserCreateRequest;
import com.mehrzad.taskmanager.dto.UserResponse;
import com.mehrzad.taskmanager.dto.UserUpdateRequest;
import com.mehrzad.taskmanager.entity.User;

import java.util.ArrayList;
import java.util.List;

public final class UserMapper {

    public static User toEntity(UserCreateRequest request) {

        User user = new User();

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setFatherName(request.getFatherName());

        return user;
    }

    public static UserResponse toResponse(User user) {

        UserResponse response = new UserResponse();

        response.setId(user.getId());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setFatherName(user.getFatherName());

        return response;
    }


    public static List<UserResponse> toResponseList(List<User> users) {

        List<UserResponse> responses = new ArrayList<>();

        for (User user : users) {
            responses.add(toResponse(user));
        }

        return responses;
    }


    public static void updateEntity(
            User user,
            UserUpdateRequest request){

        user.setFirstName(request.getFirstName());

        user.setLastName(request.getLastName());

        user.setFatherName(request.getFatherName());

        user.setEmail(request.getEmail());

    }


}