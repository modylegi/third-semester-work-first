package ru.kpfu.itis.dao.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.kpfu.itis.dao.base.UserRepository;
import ru.kpfu.itis.models.User;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    private final static String SQL_INSERT = "insert into pizza_user(email, password, role) " +
            "values (?, ?, ?)";


    private final static String SQL_GET_USER_BY_USERNAME_AND_PASSWORD = "SELECT * FROM pizza_user WHERE email = ? " +
            "AND password = MD5(?)";
    private static final String SQL_SELECT_ALL_USERS = "select * from pizza_user";
    private final static String SQL_SELECT_BY_ID = "select * from pizza_user where id = ?";
    private static final String SQL_DELETE_BY_ID = "delete from pizza_user where id = ?";
    private static final String SQL_UPDATE_USER = "update pizza_user set " +
            "email = ?, password = ?, role = ? where id = ?";
    private final static String SQL_SELECT_BY_EMAIL = "select * from pizza_user where email = ?";
    private final static String SQL_SELECT_BY_TOKEN = "select * from user_token join pizza_user  on user_token.user_id = pizza_user.id where token = ?";
    private final static String SQL_SELECT_TOKEN_BY_USER_ID = "select * from user_token where user_id = ?";
    private final static String SQL_CREATE_TOKEN = "insert into user_token(user_id, token) VALUES (?, ?)";
    private final static String SQL_UPDATE_TOKEN = "update user_token set token = ? where user_id = ?";

    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    private static final RowMapper<User> rowMapper = (row, rowNumber) -> User.builder()
            .id(row.getLong("id"))
            .email(row.getString("email"))
            .hashPassword(row.getString("password"))
            .role(row.getString("role"))
            .build();


    @Override
    public Optional<User> findById(Integer id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, rowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL_USERS, rowMapper);
    }

    @Override
    public void update(User item) {
        jdbcTemplate.update(SQL_UPDATE_USER,
                item.getId(),
                item.getEmail(),
                item.getHashPassword(),
                item.getRole());
    }

    @Override
    public User save(User item) {
        if(item.getId() == null) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement statement = connection.prepareStatement(SQL_INSERT, new String[]{"id"});
                statement.setString(1, item.getEmail());
                statement.setString(2, item.getHashPassword());
                statement.setString(3, item.getRole());
                return statement;
            }, keyHolder);
            if (keyHolder.getKey() != null) {
                item.setId(keyHolder.getKey().longValue());
            }
        } else {
            update(item);

        }
        return item;

    }

    @Override
    public void delete(Integer id) {
        jdbcTemplate.update(SQL_DELETE_BY_ID, id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_BY_EMAIL, rowMapper, email));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findByToken(String token) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_BY_TOKEN, rowMapper, token));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<String> getTokenByUserId(Long userId) {
        return jdbcTemplate.query(SQL_SELECT_TOKEN_BY_USER_ID, resultSet -> {
            if (resultSet.next()) {
                return Optional.of(resultSet.getString("token"));
            } else {
                return Optional.empty();
            }
        }, userId);
    }

    @Override
    public void createTokenForUser(Long userId, String token) {
        jdbcTemplate.update(SQL_CREATE_TOKEN, userId, token);
    }

    @Override
    public void updateTokenForUser(Long userId, String token) {
        jdbcTemplate.update(SQL_UPDATE_TOKEN, token, userId);
    }


}