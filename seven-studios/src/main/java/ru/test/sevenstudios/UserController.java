package ru.test.sevenstudios;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.test.sevenstudios.dto.UserDto;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/{userId}")
    public UserDto findUser(@PathVariable Long userId) {
        log.debug("Получен Get-запрос на получение пользователя с id {}", userId);
        return userService.getUser(userId);
    }

    @PostMapping
    public UserDto createUser(@Validated @RequestBody UserDto userDto) {
        log.debug("Получен Post-запрос на добавление пользователя {}", userDto);
        return userService.create(userDto);
    }
}
