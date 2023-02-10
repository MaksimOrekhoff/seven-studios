package ru.test.sevenstudios.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.test.sevenstudios.UserController;
import ru.test.sevenstudios.UserService;
import ru.test.sevenstudios.dto.UserDto;

import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
class UserControllerTest {
    @MockBean
    UserService userService;
    @Autowired
    MockMvc mockMvc;
    UserDto userDto;
    private final ObjectMapper mapper = new ObjectMapper();
    String path = "/users";
    String pathId = "/users/1";

    @BeforeEach
    void startParam() {
        userDto = new UserDto(
                1L,
                "johndoe@email.com",
                "John",
                "Smith",
                "First",
                "+44-12-3456-7890");
    }


    @Test
    void getUser() throws Exception {
        when(userService.getUser(1L))
                .thenReturn(userDto);

        mockMvc.perform(get(pathId)
                        .content(mapper.writeValueAsString(userDto))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(userDto.getId()), Long.class))
                .andExpect(jsonPath("$.firstName", is(userDto.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(userDto.getLastName())))
                .andExpect(jsonPath("$.middleName", is(userDto.getMiddleName())))
                .andExpect(jsonPath("$.phoneNumber", is(userDto.getPhoneNumber())))
                .andExpect(jsonPath("$.email", is(userDto.getEmail())));

        verify(userService, times(1))
                .getUser(1L);
    }

    @Test
    void create() throws Exception {
        when(userService.create(any()))
                .thenReturn(userDto);

        mockMvc.perform(post(path)
                        .content(mapper.writeValueAsString(userDto))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(userDto.getId()), Long.class))
                .andExpect(jsonPath("$.firstName", is(userDto.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(userDto.getLastName())))
                .andExpect(jsonPath("$.middleName", is(userDto.getMiddleName())))
                .andExpect(jsonPath("$.phoneNumber", is(userDto.getPhoneNumber())))
                .andExpect(jsonPath("$.email", is(userDto.getEmail())));

        verify(userService, times(1))
                .create(userDto);
    }

}