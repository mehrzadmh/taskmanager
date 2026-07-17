package com.mehrzad.taskmanager.service;

import com.mehrzad.taskmanager.dto.request.create.UserCreateRequest;
import com.mehrzad.taskmanager.dto.response.UserResponse;
import com.mehrzad.taskmanager.dto.request.update.UserUpdateRequest;
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
    private final UserMapper userMapper;
    public UserService( UserRepository userRepository,
                        UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public  UserResponse saveUser(UserCreateRequest request){
        User user = userMapper.toEntity(request);
        if(userRepository.existsByEmail(user.getEmail())){
            throw new DuplicateEmailException();
        }

        return userMapper.toResponse(userRepository.save(user));
    }

    public List<UserResponse> getAllUsers() {

        return userMapper.toResponseList(userRepository.findAll());
    }

    public UserResponse getUserById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        return  userMapper.toResponse(user);

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

        userMapper.updateEntity(user, request);
        return userMapper.toResponse(userRepository.save(user));

    }

}