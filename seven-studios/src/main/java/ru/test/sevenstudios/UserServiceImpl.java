package ru.test.sevenstudios;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.test.sevenstudios.dto.UserDto;
import ru.test.sevenstudios.exception.NotFoundException;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserDto create(UserDto userDto) {
        User user = userRepository.save(getUser(userDto));
        log.debug("Добавлен пользователь: {}", user);
        return getUserDto(user);
    }

    @Override
    public UserDto getUser(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new NotFoundException("Такой пользователь не существует.");
        }
        log.debug("Получен пользователь {}  ", user);
        return getUserDto(user.get());
    }

    private User getUser(UserDto userDto) {
        return new User(userDto.getId(),
                userDto.getEmail(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getMiddleName(),
                userDto.getPhoneNumber()
        );
    }

    private UserDto getUserDto(User user) {
        return new UserDto(user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getMiddleName(),
                user.getPhoneNumber()
        );
    }
}
