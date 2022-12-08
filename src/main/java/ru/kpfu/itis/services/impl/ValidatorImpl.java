package ru.kpfu.itis.services.impl;


import ru.kpfu.itis.dao.base.UserRepository;
import ru.kpfu.itis.dto.UserForm;
import ru.kpfu.itis.services.validation.ErrorEntity;
import ru.kpfu.itis.services.validation.Validator;

import java.util.Optional;

public class ValidatorImpl implements Validator {
    private final UserRepository userRepository;

    public ValidatorImpl(UserRepository usersRepository) {
        this.userRepository = usersRepository;
    }

    @Override
    public Optional<ErrorEntity> validateRegistration(UserForm form) {
        if(form.getEmail() == null) {
            return Optional.of(ErrorEntity.INVALID_EMAIL);
        } else if(userRepository.findByEmail(form.getEmail()).isPresent()) {
            return Optional.of(ErrorEntity.EMAIL_ALREADY_TAKEN);
        }
        return Optional.empty();
    }
}
