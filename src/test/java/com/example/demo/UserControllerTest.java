package com.example.demo;

import com.example.demo.controller.UserController;
import com.example.demo.dto.request.CreateUserRequest;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;


    @Test
    void GIVEN_aValidUser_WHEN_creatingANewUser_THEN_return200() throws Exception {

        UserResponse userResponse = new UserResponse(1L, "Ionut", "Birsan", "ionut.birsan@endava.com");

        when(userService.createUser(any(CreateUserRequest.class)))
                .thenReturn(userResponse);


        String requestJson = """
                {
                "firstName": "Ionut",
                "lastName": "Birsan",
                "email": "ionut.birsan@endava.com",
                "password": "password"
                }
                """;

        mockMvc.perform(post("/travel-journal/user")
                        .contentType("application/json")
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("Ionut"))
                .andExpect(jsonPath("$.lastName").value("Birsan"))
                .andExpect(jsonPath("$.email").value("ionut.birsan@endava.com"));

    }

    @Test
    void GIVEN_aUserWithoutFirstName_WHEN_creatingANewUser_THEN_return400() throws Exception {

        UserResponse userResponse = new UserResponse(1L, "Ionut", "Birsan", "ionut.birsan@endava.com");

        when(userService.createUser(any(CreateUserRequest.class)))
                .thenReturn(userResponse);


        String requestJson = """
                {
                "lastName": "Birsan",
                "email": "ionut.birsan@endava.com",
                "password": "password"
                }
                """;

        mockMvc.perform(post("/travel-journal/user")
                        .contentType("application/json")
                        .content(requestJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void GIVEN_aUserWithoutLastName_WHEN_creatingANewUser_THEN_return400() throws Exception {

        UserResponse userResponse = new UserResponse(1L,"Ionut","Birsan","ionut.birsan@endava.com");

        when(userService.createUser(any(CreateUserRequest.class)))
                .thenReturn(userResponse);


        String requestJson= """
                {
                "firstName": "Ionut",
                "email": "ionut.birsan@endava.com",
                "password": "password"
                """;

        mockMvc.perform(post("/travel-journal/user")
                        .contentType("application/json")
                        .content(requestJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void GIVEN_aUserWithoutEmail_WHEN_creatingANewUser_THEN_return400() throws Exception {

        UserResponse userResponse = new UserResponse(1L,"Ionut","Birsan","ionut.birsan@endava.com");

        when(userService.createUser(any(CreateUserRequest.class)))
                .thenReturn(userResponse);


        String requestJson= """
                {
                "firstName": "Ionut",
                "lastName": "Birsan",
                "password": "password"
                }
                """;

        mockMvc.perform(post("/travel-journal/user")
                        .contentType("application/json")
                        .content(requestJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void GIVEN_aUserWithoutPassword_WHEN_creatingANewUser_THEN_return400() throws Exception {

        UserResponse userResponse = new UserResponse(1L,"Ionut","Birsan","ionut.birsan@endava.com");

        when(userService.createUser(any(CreateUserRequest.class)))
                .thenReturn(userResponse);


        String requestJson= """
                {
                "firstName": "Ionut",
                "lastName": "Birsan",
                "email": "ionut.birsan@endava.com",
                }
                """;

        mockMvc.perform(post("/travel-journal/user")
                        .contentType("application/json")
                        .content(requestJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void GIVEN_aUserWithInvalidEmail_WHEN_creatingANewUser_THEN_return400() throws Exception {

        UserResponse userResponse = new UserResponse(1L,"Ionut","Birsan","ionut.birsan@endava.com");

        when(userService.createUser(any(CreateUserRequest.class)))
                .thenReturn(userResponse);


        String requestJson= """
                {
                "firstName": "Ionut",
                "lastName": "Birsan",
                "password": "password"
                "email": "bla",
                }
                """;

        mockMvc.perform(post("/travel-journal/user")
                        .contentType("application/json")
                        .content(requestJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void GIVEN_aUserWithFieldsOver50Characters_WHEN_creatingANewUser_THEN_return400() throws Exception {

        UserResponse userResponse = new UserResponse(1L,"Ionut","Birsan","ionut.birsan@endava.com");

        when(userService.createUser(any(CreateUserRequest.class)))
                .thenReturn(userResponse);


        String requestJson= """
                {
                "firstName": "IonutIonutIonutIonutIonutIonutIonutIonutIonutIonutIonutIonut",
                "lastName": "BirsanBirsanBirsanBirsanBirsanBirsanBirsanBirsanBirsanBirsan",
                "password": "passwordpasswordpasswordpasswordpasswordpasswordpasswordpassword"
                "email": "ionut.birsan@endava.com",
                }
                """;

        mockMvc.perform(post("/travel-journal/user")
                        .contentType("application/json")
                        .content(requestJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void GIVEN_aUserWithFieldsUnder2Characters_WHEN_creatingANewUser_THEN_return400() throws Exception {

        UserResponse userResponse = new UserResponse(1L,"Ionut","Birsan","ionut.birsan@endava.com");

        when(userService.createUser(any(CreateUserRequest.class)))
                .thenReturn(userResponse);


        String requestJson= """
                {
                "firstName": "I",
                "lastName": "B",
                "password": "password"
                "email": "ionut.birsan@endava.com",
                }
                """;

        mockMvc.perform(post("/travel-journal/user")
                        .contentType("application/json")
                        .content(requestJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void GIVEN_aUserWithPasswordUnder5Characters_WHEN_creatingANewUser_THEN_return400() throws Exception {

        UserResponse userResponse = new UserResponse(1L,"Ionut","Birsan","ionut.birsan@endava.com");

        when(userService.createUser(any(CreateUserRequest.class)))
                .thenReturn(userResponse);


        String requestJson= """
                {
                "firstName": "Ionut",
                "lastName": "Birsan",
                "email": "ionut.birsan@endava.com",
                "password": "pass"
                }
                """;

        mockMvc.perform(post("/travel-journal/user")
                        .contentType("application/json")
                        .content(requestJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void GIVEN_aUserNotAJson_WHEN_creatingANewUser_THEN_return400() throws Exception {

        UserResponse userResponse = new UserResponse(1L,"Ionut","Birsan","ionut.birsan@endava.com");

        when(userService.createUser(any(CreateUserRequest.class)))
                .thenReturn(userResponse);


        String requestJson= "bla string";

        mockMvc.perform(post("/travel-journal/user")
                        .contentType("application/json")
                        .content(requestJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void GIVEN_anExistingUserInDB_WHEN_gettingAnUser_THEN_returnTheUser() throws Exception {

        UserResponse userResponse = new UserResponse(1L,"Ionut","Birsan","ionut.birsan@endava.com");

        when(userService.getUser(userResponse.id()))
                .thenReturn(userResponse);

        mockMvc.perform(get("/travel-journal/user/{id}", userResponse.id()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("Ionut"))
                .andExpect(jsonPath("$.lastName").value("Birsan"))
                .andExpect(jsonPath("$.email").value("ionut.birsan@endava.com"));
    }

    @Test
    void GIVEN_anUserNotInDB_WHEN_gettingAnUser_THEN_return404() throws Exception {

        UserResponse userResponse = new UserResponse(1L,"Ionut","Birsan","ionut.birsan@endava.com");

        when(userService.getUser(2L))
                .thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "The user was not found"));

        mockMvc.perform(get("/travel-journal/user/{id}", 2))
                .andExpect(status().isNotFound());
    }

}
