package ru.test.sevenstudios.user;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.test.sevenstudios.UserRepository;
import ru.test.sevenstudios.UserServiceImpl;
import ru.test.sevenstudios.dto.UserDto;
import ru.test.sevenstudios.exception.NotFoundException;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@RequiredArgsConstructor(onConstructor_ = @Autowired)
@SpringBootTest
@Transactional
class UserServiceImplTest {
    private final UserRepository userRepository;
    private final UserServiceImpl userService;
    UserDto userDto;

    @Test
    void create() {
        UserDto newUser = userService.create(userDto);

        assertEquals(newUser.getFirstName(), userDto.getFirstName());
        assertEquals(newUser.getLastName(), userDto.getLastName());
        assertEquals(newUser.getMiddleName(), userDto.getMiddleName());
        assertEquals(newUser.getPhoneNumber(), userDto.getPhoneNumber());
        assertEquals(newUser.getEmail(), userDto.getEmail());
    }

    @Test
    void findByIdExceptionUser() {
        NotFoundException thrown = Assertions.assertThrows(NotFoundException.class, () -> userService.getUser(100L));

        Assertions.assertEquals("Такой пользователь не существует.", thrown.getMessage());
    }


    @Test
    void findByIdUser() {
        UserDto newUser = userService.create(userDto);

        UserDto result = userService.getUser(newUser.getId());

        assertEquals(newUser.getId(), result.getId());
        assertEquals(newUser.getFirstName(), result.getFirstName());
        assertEquals(newUser.getLastName(), result.getLastName());
        assertEquals(newUser.getMiddleName(), result.getMiddleName());
        assertEquals(newUser.getPhoneNumber(), result.getPhoneNumber());
        assertEquals(newUser.getEmail(), result.getEmail());
    }


    @AfterEach
    void clean() {
        userRepository.deleteAll();
    }

    @BeforeEach
    void start() {
        userDto = new UserDto(
                1L,
                "johndoe@email.com",
                "John",
                "Smith",
                "First",
                "+44-12-3456-7890");
    }

}