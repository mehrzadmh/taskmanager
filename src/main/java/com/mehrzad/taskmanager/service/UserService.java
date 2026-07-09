package com.mehrzad.taskmanager.service;

import com.mehrzad.taskmanager.dto.UserCreateRequest;
import com.mehrzad.taskmanager.dto.UserResponse;
import com.mehrzad.taskmanager.dto.UserUpdateRequest;
import com.mehrzad.taskmanager.entity.User;
import com.mehrzad.taskmanager.exception.DuplicateEmailException;
import com.mehrzad.taskmanager.exception.UserNotFoundException;
import com.mehrzad.taskmanager.mapper.UserMapper;
import com.mehrzad.taskmanager.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService( UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public  UserResponse saveUser(UserCreateRequest request){
        User user = UserMapper.toEntity(request);
        if(userRepository.existsByEmail(user.getEmail())){
            throw new DuplicateEmailException();
        }

        return UserMapper.toResponse(userRepository.save(user));
    }

    public List<UserResponse> getAllUsers() {

        return UserMapper.toResponseList(userRepository.findAll());
    }

    public UserResponse getUserById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        return  UserMapper.toResponse(user);

    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }




    public UserResponse updateUser(Long id, UserUpdateRequest request ){

        User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        if(userRepository.existsByEmailAndIdNot(request.getEmail(), id)){
            throw new DuplicateEmailException();
        }

        UserMapper.updateEntity(user, request);
        return UserMapper.toResponse(userRepository.save(user));

    }

}