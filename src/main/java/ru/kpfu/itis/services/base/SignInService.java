package ru.kpfu.itis.services.base;

import ru.kpfu.itis.dto.UserDto;
import ru.kpfu.itis.dto.UserForm;

public interface SignInService {
    UserDto signIn(UserForm userForm);
    UserDto signIn(String token);
}
