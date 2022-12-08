package ru.kpfu.itis.dao.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.kpfu.itis.dao.base.PizzaRepository;

import ru.kpfu.itis.models.Pizza;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.*;

public class PizzaRepositoryImpl implements PizzaRepository {



    private final static String SQL_UPDATE_PIZZA = "update pizza set pizza_name = ?, ingredients = ?, price = ?, photo_path = ? where id = ?";
    private final static String SQL_INSERT = "insert into pizza (pizza_name, ingredients, price, photo_path) VALUES (?, ?, ?, ?)";
    private static final String SQL_SELECT_ALL_PIZZA = "select * from pizza";
    private final static String SQL_SELECT_BY_ID = "select * from pizza where id = ?";
    private static final String SQL_DELETE_BY_ID = "delete from pizza where id = ?";



    private final JdbcTemplate jdbcTemplate;

    public PizzaRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private static final RowMapper<Pizza> rowMapper = (row, rowNumber) -> Pizza.builder()
            .id(row.getLong("id"))
            .pizza_name(row.getString("pizza_name"))
            .ingredients(row.getString("ingredients"))
            .price(row.getInt("price"))
            .photoPath(row.getString("photo_path"))
            .build();




    @Override
    public void update(Pizza item) {
        jdbcTemplate.update(SQL_UPDATE_PIZZA,
                item.getPizza_name(),
                item.getIngredients(),
                item.getPrice(),
                item.getPhotoPath(),
                item.getId()
                );

    }


    @Override
    public Pizza save(Pizza item)  {
        if(item.getId() == null) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement statement = connection.prepareStatement(SQL_INSERT, new String[]{"id"});
                statement.setString(1, item.getPizza_name());
                statement.setString(2, item.getIngredients());
                statement.setInt(3, item.getPrice());
                statement.setString(4, item.getPhotoPath());
                return statement;
            }, keyHolder);
            if (keyHolder.getKey() != null) {
                item.setId(keyHolder.getKey().longValue());
            }
        } else{
            update(item);
        }
        return item;
    }

    @Override
    public void delete(Long id)  {
        jdbcTemplate.update(SQL_DELETE_BY_ID, id);
    }

    @Override
    public Optional<Pizza> findById(Long id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, rowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Pizza> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL_PIZZA, rowMapper);
    }

}
