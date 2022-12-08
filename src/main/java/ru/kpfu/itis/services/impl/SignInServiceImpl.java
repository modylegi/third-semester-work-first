package ru.kpfu.itis.services.impl;

import ru.kpfu.itis.dao.base.UserRepository;
import ru.kpfu.itis.dto.UserDto;
import ru.kpfu.itis.dto.UserForm;
import ru.kpfu.itis.exceptions.ValidationException;
import ru.kpfu.itis.models.User;
import ru.kpfu.itis.services.base.PasswordEncoder;
import ru.kpfu.itis.services.base.SignInService;
import ru.kpfu.itis.services.validation.ErrorEntity;


import java.util.UUID;

public class SignInServiceImpl implements SignInService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SignInServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto signIn(UserForm userForm) {
        User user = userRepository.findByEmail(userForm.getEmail())
                .orElseThrow(() -> new ValidationException(ErrorEntity.NOT_FOUND));
        if (passwordEncoder.matches(userForm.getPassword(), user.getHashPassword()) == false) {
            throw new ValidationException(ErrorEntity.INCORRECT_PASSWORD);
        }
        UserDto userDto = UserDto.from(user);
        String token = UUID.randomUUID().toString();
        if(userRepository.getTokenByUserId(user.getId()).isPresent()) {
            userRepository.updateTokenForUser(user.getId(), token);
        } else {
            userRepository.createTokenForUser(user.getId(), token);
        }
        userDto.setToken(token);
        return userDto;
    }

    @Override
    public UserDto signIn(String token) {
        User user = userRepository.findByToken(token)
                .orElseThrow(() -> new ValidationException(ErrorEntity.NOT_FOUND));
        return UserDto.from(user);
    }
}
