package ru.kpfu.itis.services.impl;

import ru.kpfu.itis.dao.base.UserRepository;
import ru.kpfu.itis.dto.UserForm;
import ru.kpfu.itis.exceptions.ValidationException;
import ru.kpfu.itis.models.User;
import ru.kpfu.itis.services.base.PasswordEncoder;
import ru.kpfu.itis.services.base.SignUpService;
import ru.kpfu.itis.services.validation.ErrorEntity;
import ru.kpfu.itis.services.validation.Validator;

import java.util.Optional;

public class SignUpServiceImpl implements SignUpService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Validator validator;

    public SignUpServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, Validator validator) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.validator = validator;
    }

    @Override
    public void signUp(UserForm form) {
        // passwordEncoder.matches("123123", "HASH");
        Optional<ErrorEntity> optionalError = validator.validateRegistration(form);
        if(optionalError.isPresent()) {
            throw new ValidationException(optionalError.get());
        }
        User user = User.builder()
                .email(form.getEmail())
                .hashPassword(passwordEncoder.encode(form.getPassword()))
                .role(form.getRole())
                .build();
        userRepository.save(user);
    }
}
