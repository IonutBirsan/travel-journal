package com.example.demo;

import com.example.demo.controller.UserController;
import com.example.demo.dto.request.CreateUserRequest;
import com.example.demo.dto.request.LoginRequest;
import com.example.demo.dto.request.UpdateUserRequest;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.MediaType;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @Nested
    class CreateUser {

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

            UserResponse userResponse = new UserResponse(1L, "Ionut", "Birsan", "ionut.birsan@endava.com");

            when(userService.createUser(any(CreateUserRequest.class)))
                    .thenReturn(userResponse);


            String requestJson = """
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

            UserResponse userResponse = new UserResponse(1L, "Ionut", "Birsan", "ionut.birsan@endava.com");

            when(userService.createUser(any(CreateUserRequest.class)))
                    .thenReturn(userResponse);


            String requestJson = """
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

            UserResponse userResponse = new UserResponse(1L, "Ionut", "Birsan", "ionut.birsan@endava.com");

            when(userService.createUser(any(CreateUserRequest.class)))
                    .thenReturn(userResponse);


            String requestJson = """
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

            UserResponse userResponse = new UserResponse(1L, "Ionut", "Birsan", "ionut.birsan@endava.com");

            when(userService.createUser(any(CreateUserRequest.class)))
                    .thenReturn(userResponse);


            String requestJson = """
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

            UserResponse userResponse = new UserResponse(1L, "Ionut", "Birsan", "ionut.birsan@endava.com");

            when(userService.createUser(any(CreateUserRequest.class)))
                    .thenReturn(userResponse);


            String requestJson = """
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

            UserResponse userResponse = new UserResponse(1L, "Ionut", "Birsan", "ionut.birsan@endava.com");

            when(userService.createUser(any(CreateUserRequest.class)))
                    .thenReturn(userResponse);


            String requestJson = """
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

            UserResponse userResponse = new UserResponse(1L, "Ionut", "Birsan", "ionut.birsan@endava.com");

            when(userService.createUser(any(CreateUserRequest.class)))
                    .thenReturn(userResponse);


            String requestJson = """
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

            UserResponse userResponse = new UserResponse(1L, "Ionut", "Birsan", "ionut.birsan@endava.com");

            when(userService.createUser(any(CreateUserRequest.class)))
                    .thenReturn(userResponse);


            String requestJson = "bla string";

            mockMvc.perform(post("/travel-journal/user")
                            .contentType("application/json")
                            .content(requestJson))
                    .andExpect(status().isBadRequest());
        }
    }

    @Nested
    class GetUser {

        @Test
        void GIVEN_anExistingUserInDB_WHEN_gettingAnUser_THEN_returnTheUser() throws Exception {

            UserResponse userResponse = new UserResponse(1L, "Ionut", "Birsan", "ionut.birsan@endava.com");

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

            UserResponse userResponse = new UserResponse(1L, "Ionut", "Birsan", "ionut.birsan@endava.com");

            when(userService.getUser(2L))
                    .thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "The user was not found"));

            mockMvc.perform(get("/travel-journal/user/{id}", 2))
                    .andExpect(status().isNotFound());
        }

        @Test
        void GIVEN_aRequestWithInvalidId_WHEN_gettingAnUser_THEN_return404() throws Exception {

            mockMvc.perform(get("/travel-journal/user/{id}", "bla"))
                    .andExpect(status().isBadRequest());
        }

        @Test
        void GIVEN_existingUser_WHEN_gettingAnUser_THEN_passwordIsNotReturned() throws Exception {

            UserResponse userResponse =
                    new UserResponse(1L, "Ionut", "Birsan", "ionut.birsan@endava.com");

            when(userService.getUser(1L))
                    .thenReturn(userResponse);

            mockMvc.perform(get("/travel-journal/user/{id}", 1))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.password").doesNotExist());
        }
    }

    @Nested
    class GetUsers {

        @Test
        void GIVEN_usersExistingInDb_WHEN_gettingAllUsers_THEN_returnAllUsers() throws Exception {

            List<UserResponse> usersList = List.of(
                    new UserResponse(1L, "Ionut", "Birsan", "ionut.birsan@endava.com"),
                    new UserResponse(2L, "Ionutt", "Birsann", "ionut.birsan2@endava.com"));

            when(userService.getAllUsers())
                    .thenReturn(usersList);

            mockMvc.perform(get("/travel-journal/users"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].id").value(1))
                    .andExpect(jsonPath("$[0].firstName").value("Ionut"))
                    .andExpect(jsonPath("$[0].lastName").value("Birsan"))
                    .andExpect(jsonPath("$[0].email").value("ionut.birsan@endava.com"))
                    .andExpect(jsonPath("$[1].id").value(2))
                    .andExpect(jsonPath("$[1].firstName").value("Ionutt"))
                    .andExpect(jsonPath("$[1].lastName").value("Birsann"))
                    .andExpect(jsonPath("$[1].email").value("ionut.birsan2@endava.com"));

        }

        @Test
        void GIVEN_2usersExistingInDb_WHEN_gettingAllUsers_THEN_return2lUsersAndNoPasswords() throws Exception {  //merge legata cu cea de mai sus

            List<UserResponse> usersList = List.of(
                    new UserResponse(1L, "Ionut", "Birsan", "ionut.birsan@endava.com"),
                    new UserResponse(2L, "Ionutt", "Birsann", "ionut.birsan2@endava.com"));

            when(userService.getAllUsers())
                    .thenReturn(usersList);

            mockMvc.perform(get("/travel-journal/users"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.length()").value(2))
                    .andExpect(jsonPath("$[0].password").doesNotExist())
                    .andExpect(jsonPath("$[1].password").doesNotExist());
        }

        @Test
        void GIVEN_0usersExistingInDb_WHEN_gettingAllUsers_THEN_returnEmptyList() throws Exception {  //merge legata cu cea de mai sus

            when(userService.getAllUsers())
                    .thenReturn(List.of());

            mockMvc.perform(get("/travel-journal/users"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.length()").value(0));
        }

    }

    @Nested
    class DeleteUser {

        @Test
        void GIVEN_aValidUserInDB_WHEN_deletingAnUser_THEN_return200() throws Exception {

            doNothing().when(userService).deleteUser(1L);

            mockMvc.perform(delete("/travel-journal/user/{id}", 1L))
                    .andExpect(status().isOk());
        }

        @Test
        void GIVEN_aUserNotInDB_WHEN_deletingAnUser_THEN_return404() throws Exception {

            doThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found"))
                    .when(userService).deleteUser(1L);

            mockMvc.perform(delete("/travel-journal/user/{id}", 1L))
                    .andExpect(status().isNotFound());
        }
    }

    @Test
    void GIVEN_invalidId_WHEN_deletingAnUser_THEN_return400() throws Exception {

        mockMvc.perform(delete("/travel-journal/user/{id}", "bla"))
                .andExpect(status().isBadRequest());
    }

    @Nested
    class ModifyUser {

        @Test
        void GIVEN_anExistingUserInDB_WHEN_modifyingUser_THEN_updateTheUserAndReturnIt() throws Exception {
            UserResponse updatedUser = new UserResponse(1L,"Ionut","Birsan","ionut.birsan@endava.com");

            when(userService.modifyUser(anyLong(), any(UpdateUserRequest.class)))
                    .thenReturn(updatedUser);

            String requestJson = """
            {
              "firstName": "Ionut",
              "lastName": "Birsan",
              "email": "ionut.birsan@endava.com",
              "password": "password"
            }
            """;


            mockMvc.perform(put("/travel-journal/user/{id}", 1)
                    .contentType("application/json")
                    .content(requestJson))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(1))
                    .andExpect(jsonPath("$.firstName").value("Ionut"))
                    .andExpect(jsonPath("$.lastName").value("Birsan"))
                    .andExpect(jsonPath("$.email").value("ionut.birsan@endava.com"));
        }

        @Test
        void GIVEN_anUserNotInDB_WHEN_modifyingUser_THEN_return404() throws Exception {

            when(userService.modifyUser(anyLong(), any(UpdateUserRequest.class)))
                    .thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

            String requestJson = """
            {
              "firstName": "Ionut",
              "lastName": "Birsan",
              "email": "ionut.birsan@endava.com",
              "password": "password"
            }
            """;


            mockMvc.perform(put("/travel-journal/user/{id}", 1)
                            .contentType("application/json")
                            .content(requestJson))
                    .andExpect(status().isNotFound());
        }

        @Test
        void GIVEN_anInvalidRequest_WHEN_modifyingUser_THEN_return400() throws Exception {

            String requestJson = """
            {
              "firstName": "Ionut",
              "lastName": "Birsan",
              "email": "io",
              "password": "pa"
            }
            """;                         //invalid email and pass


            mockMvc.perform(put("/travel-journal/user/{id}", 1)
                            .contentType("application/json")
                            .content(requestJson))
                    .andExpect(status().isBadRequest());
        }

    }

    @Nested
    class LoginUser {

        @Test
        void GIVEN_anValidUserInDB_WHEN_login_THEN_returnThatUserWithoutPassword() throws Exception {

            UserResponse userResponse =
                    new UserResponse(1L, "Ionut", "Birsan", "ionut.birsan@endava.com");

            when(userService.login(any(LoginRequest.class)))
                    .thenReturn(userResponse);

            String requestJson = """
            {
              "email": "ionut.birsan@endava.com",
              "password": "password"
            }
            """;

            mockMvc.perform(post("/travel-journal/login")
                    .contentType("application/json")
                    .content(requestJson))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(1))
                    .andExpect(jsonPath("$.firstName").value("Ionut"))
                    .andExpect(jsonPath("$.lastName").value("Birsan"))
                    .andExpect(jsonPath("$.email").value("ionut.birsan@endava.com"))
                    .andExpect(jsonPath("$.password").doesNotExist());

        }

        @Test
        void GIVEN_anValidUserInDBandAWrongPassword_WHEN_login_THEN_return401() throws Exception {

            when(userService.login(any(LoginRequest.class)))
                    .thenThrow(new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials"));


            String requestJson = """
            {
              "email": "ionut.birsan@endava.com",
              "password": "badPassword"
            }
            """;

            mockMvc.perform(post("/travel-journal/login")
                            .contentType("application/json")
                            .content(requestJson))
                    .andExpect(status().isUnauthorized());
        }

        @Test
        void GIVEN_unknownEmail_WHEN_login_THEN_return401() throws Exception {

            when(userService.login(any(LoginRequest.class)))
                    .thenThrow(new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials"));

            String requestJson = """
            {
              "email": "unknown@endava.com",
              "password": "password"
            }
            """;

            mockMvc.perform(post("/travel-journal/login")
                            .contentType("application/json")
                            .content(requestJson))
                    .andExpect(status().isUnauthorized());
        }

        @Test
        void GIVEN_aRequestWithoutEmail_WHEN_login_THEN_return400() throws Exception {

            String requestJson = """
            {
              "password": "password"
            }
            """;

            mockMvc.perform(post("/travel-journal/login")
                            .contentType("application/json")
                            .content(requestJson))
                    .andExpect(status().isBadRequest());
        }

        @Test
        void GIVEN_invalidEmailFormat_WHEN_login_THEN_return400() throws Exception {

            String requestJson = """
            {
              "email": "io",
              "password": "password"
            }
            """;

            mockMvc.perform(post("/travel-journal/login")
                            .contentType("application/json")
                            .content(requestJson))
                    .andExpect(status().isBadRequest());
        }

        @Test
        void GIVEN_aRequestWithMissingPassword_WHEN_login_THEN_return400() throws Exception {

            String requestJson = """
            {
              "email": "ionut.birsan@endava.com"
            }
            """;

            mockMvc.perform(post("/travel-journal/login")
                            .contentType("application/json")
                            .content(requestJson))
                    .andExpect(status().isBadRequest());
        }
    }
}
