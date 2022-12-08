package ru.kpfu.itis.services.base;

public interface PasswordEncoder {
    boolean matches(String password, String hashPassword);
    String encode(String password);
}
