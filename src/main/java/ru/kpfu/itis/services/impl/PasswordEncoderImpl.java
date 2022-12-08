package ru.kpfu.itis.services.impl;


import ru.kpfu.itis.services.base.PasswordEncoder;

public class PasswordEncoderImpl implements PasswordEncoder {
    @Override
    public boolean matches(String password, String hashPassword) {
        return password.equals(hashPassword);
    }

    @Override
    public String encode(String password) {
        return password;
    }
}
