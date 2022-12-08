package ru.kpfu.itis.dao.base;

import ru.kpfu.itis.models.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    Optional<User> findByToken(String token);
    Optional<String> getTokenByUserId(Long userId);
    void createTokenForUser(Long userId, String token);
    void updateTokenForUser(Long userId, String token);

}
