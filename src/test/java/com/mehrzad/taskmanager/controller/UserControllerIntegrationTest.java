package com.mehrzad.taskmanager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mehrzad.taskmanager.dto.request.create.UserCreateRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Transactional
    void shouldCreateUserSuccessfully() throws Exception {

        UserCreateRequest request = new UserCreateRequest();
        request.setFirstName("mehrzad");
        request.setLastName("hajkhalil");
        request.setEmail("meh3hajkhalil@test.com");
        request.setFatherName("mahdi");


        String json = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value("mehrzad"))
                .andExpect(jsonPath("$.lastName").value("hajkhalil"))
                .andExpect(jsonPath("$.email").value("meh3hajkhalil@test.com"))
                .andExpect(jsonPath("$.id").isNumber());

    }


    @Test
    @Transactional
    void shouldReturnConflictWhenEmailAlreadyExists() throws Exception {

        // Arrange

        UserCreateRequest request = new UserCreateRequest();
        request.setFirstName("mehrzad");
        request.setLastName("hajkhalil");
        request.setEmail("meh3hajkhalil@test.com");
        request.setFatherName("mahdi");


        String json = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value("mehrzad"))
                .andExpect(jsonPath("$.lastName").value("hajkhalil"))
                .andExpect(jsonPath("$.email").value("meh3hajkhalil@test.com"))
                .andExpect(jsonPath("$.id").isNumber());

        mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(json)).andDo(print())
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value("Email already exists."));


    }

    /*@Test
    @Transactional
    void  shouldReturnBadRequestWhenFirstNameIsBlank() throws  Exception{

        UserCreateRequest request = new UserCreateRequest();
        request.setFirstName("");
        request.setLastName("Sdgdsgdsg");
        request.setEmail("meh3hajkhalil@test.com");
        request.setFatherName("mahdi");
        request.setPassword("23523");

        String json = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(json)).andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.errors.firstName").value("First name is required"));

    }*/

}