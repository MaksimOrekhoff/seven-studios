package ru.test.sevenstudios;

import ru.test.sevenstudios.dto.UserDto;

public interface UserService {
    UserDto create(UserDto userDto);

    UserDto getUser(Long userId);
}
