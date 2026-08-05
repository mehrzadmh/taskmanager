package com.mehrzad.taskmanager.service;

import com.mehrzad.taskmanager.dto.request.create.UserCreateRequest;
import com.mehrzad.taskmanager.dto.request.update.UserUpdateRequest;
import com.mehrzad.taskmanager.dto.response.UserResponse;
import com.mehrzad.taskmanager.entity.User;
import com.mehrzad.taskmanager.exception.DuplicateEmailException;
import com.mehrzad.taskmanager.exception.UserNotFoundException;
import com.mehrzad.taskmanager.mapper.UserMapper;
import com.mehrzad.taskmanager.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldFindUserById() {

        ////َArrange
        User user = new User();
        user.setId(1L);
        user.setFirstName("Mehrzad");
        user.setLastName("HajKhalil");

        UserResponse response = new UserResponse();
        response.setId(1L);
        response.setFirstName("Mehrzad");
        response.setLastName("HajKhalil");

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));

        when(userMapper.toResponse(user))
                .thenReturn(response);

        ///Act
        UserResponse result = userService.getUserById(1L);

        ////Assert
        assertNotNull(result);

        assertEquals(1L, result.getId());
        assertEquals("Mehrzad", result.getFirstName());
        assertEquals("HajKhalil", result.getLastName());


        ///Verify
        verify(userRepository).findById(1L);
        verify(userMapper).toResponse(user);

    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {

        // Arrange
        when(userRepository.findById(2L))
                .thenReturn(Optional.empty());

        // Act + Assert
        assertThrows(
                UserNotFoundException.class,
                () -> userService.getUserById(2L)
        );

        verify(userRepository).findById(2L);
        verify(userMapper,never()).toResponse(any());
    }


    @Test
    void shouldSaveUser() {

        ////َArrange
        UserCreateRequest userCreateRequest = new UserCreateRequest();

        User user = new User();
        user.setId(1L);
        user.setFirstName("Mehrzad");
        user.setLastName("HajKhalil");
        user.setEmail("Mehrzad@test.com");

        UserResponse response = new UserResponse();
        response.setId(1L);
        response.setFirstName("Mehrzad");
        response.setLastName("HajKhalil");
        response.setEmail("Mehrzad@test.com");


        when(userMapper.toEntity(userCreateRequest))
                .thenReturn(user);

        when(userRepository.existsByEmail(user.getEmail()))
                .thenReturn(false);

        when(userRepository.save(user))
                .thenReturn(user);

        when(userMapper.toResponse(user))
                .thenReturn(response);



        ///Act
        UserResponse result = userService.saveUser(userCreateRequest);

        ////Assert
        assertNotNull(result);

        assertEquals(1L, result.getId());
        assertEquals("Mehrzad", result.getFirstName());
        assertEquals("HajKhalil", result.getLastName());
        assertEquals("Mehrzad@test.com", result.getEmail());


        ///Verify
        verify(userMapper).toEntity(userCreateRequest);
        verify(userRepository).existsByEmail(user.getEmail());
        verify(userRepository).save(user);
        verify(userMapper).toResponse(user);
    }

    @Test
    void shouldThrowExceptionWhenExistsByEmail() {


        UserCreateRequest userCreateRequest = new UserCreateRequest();

        User user = new User();
        user.setId(1L);
        user.setFirstName("Mehrzad");
        user.setLastName("HajKhalil");
        user.setEmail("Mehrzad@test.com");

        when(userMapper.toEntity(userCreateRequest))
                .thenReturn(user);

        when(userRepository.existsByEmail(user.getEmail()))
                .thenReturn(true);

        // Act + Assert
        assertThrows(
                DuplicateEmailException.class,
                () -> userService.saveUser(userCreateRequest)
        );

        ///Verify
        verify(userMapper).toEntity(userCreateRequest);
        verify(userRepository).existsByEmail(user.getEmail());
        verify(userRepository,never()).save(any());
        verify(userMapper,never()).toResponse(any());
    }

    @Test
    void shouldUpdateUser() {

        ////َArrange
        UserUpdateRequest userUpdateRequest = new UserUpdateRequest();
        userUpdateRequest.setFirstName("Mehrzad");
        userUpdateRequest.setLastName("HajKhalil");
        userUpdateRequest.setEmail("Mehrzad@test.com");


        User user = new User();
        user.setId(1L);
        user.setFirstName("Mehrzad");
        user.setLastName("HajKhalil");
        user.setEmail("Mehrzad@test.com");

        UserResponse response = new UserResponse();
        response.setId(1L);
        response.setFirstName("Mehrzad");
        response.setLastName("HajKhalil");
        response.setEmail("Mehrzad@test.com");



        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));

        when(userRepository.existsByEmailAndIdNot(user.getEmail(),1L))
                .thenReturn(false);

        doNothing().when(userMapper)
                .updateEntity(user, userUpdateRequest);

        when(userRepository.save(user))
                .thenReturn(user);

        when(userMapper.toResponse(user))
                .thenReturn(response);



        ///Act
        UserResponse result = userService.updateUser(1L,userUpdateRequest);


        ////Assert
        assertNotNull(result);

        assertEquals(1L, result.getId());
        assertEquals("Mehrzad", result.getFirstName());
        assertEquals("HajKhalil", result.getLastName());
        assertEquals("Mehrzad@test.com", result.getEmail());


        ///Verify
        verify(userRepository).findById(1L);
        verify(userRepository).existsByEmailAndIdNot(user.getEmail(),1L);
        verify(userMapper).updateEntity(user, userUpdateRequest);
        verify(userRepository).save(user);
        verify(userMapper).toResponse(user);
    }

    @Test
    void shouldThrowExceptionWhenUserNotFoundForUpdateUser() {

        ////َArrange
        UserUpdateRequest userUpdateRequest = new UserUpdateRequest();

        when(userRepository.findById(1L))
                .thenReturn(Optional.empty());

        // Act + Assert
        assertThrows(
                UserNotFoundException.class,
                () -> userService.updateUser(1L,userUpdateRequest)
        );

        ///Verify
        verify(userRepository).findById(1L);
        verify(userRepository, never())
                .existsByEmailAndIdNot(any(), anyLong());

        verify(userMapper, never())
                .updateEntity(any(), any());

        verify(userRepository, never())
                .save(any());

        verify(userMapper, never())
                .toResponse(any());
    }
    @Test
    void shouldThrowExceptionWhenExistsByEmailForUpdate() {

        ////َArrange
        UserUpdateRequest userUpdateRequest = new UserUpdateRequest();
        userUpdateRequest.setFirstName("Mehrzad");
        userUpdateRequest.setLastName("HajKhalil");
        userUpdateRequest.setEmail("Mehrzad@test.com");


        User user = new User();
        user.setId(1L);
        user.setFirstName("Mehrzad");
        user.setLastName("HajKhalil");
        user.setEmail("Mehrzad@test.com");

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));

        when(userRepository.existsByEmailAndIdNot(user.getEmail(),1L))
                .thenReturn(true);

        // Act + Assert
        assertThrows(
                DuplicateEmailException.class,
                () -> userService.updateUser(1L,userUpdateRequest)
        );


        ///Verify
        verify(userRepository).findById(1L);
        verify(userRepository).existsByEmailAndIdNot(user.getEmail(),1L);

        verify(userMapper, never())
                .updateEntity(any(), any());

        verify(userRepository, never())
                .save(any());

        verify(userMapper, never())
                .toResponse(any());
    }
}