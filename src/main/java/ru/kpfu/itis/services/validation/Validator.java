package ru.kpfu.itis.services.validation;


import ru.kpfu.itis.dto.UserForm;

import java.util.Optional;

public interface Validator {
    Optional<ErrorEntity> validateRegistration(UserForm form);
}
