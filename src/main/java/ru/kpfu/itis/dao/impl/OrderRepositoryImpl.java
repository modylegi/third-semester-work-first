package ru.kpfu.itis.dao.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.kpfu.itis.dao.base.OrderRepository;
import ru.kpfu.itis.models.Order;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

public class OrderRepositoryImpl implements OrderRepository {

    private final static String SQL_INSERT = "insert into pizza_order (user_id, quantity, list_of_ordered_pizza, " +
            "order_cost, date_of_order) VALUES (?, ?, ?, ?, ?)";
    private final static String SQL_SELECT_BY_ID = "select * from pizza_order where order_id = ?";

    private static final String SQL_SELECT_ALL_PIZZA = "select * from pizza_order";

    private static final String SQL_DELETE_BY_ID = "delete from pizza_order where order_id = ?";
    private final static String SQL_UPDATE_ORDER = "update pizza_order set user_id = ?, quantity = ?," +
            " list_of_ordered_pizza = ?, order_cost = ?, date_of_order = ? where order_id = ?";

    private final JdbcTemplate jdbcTemplate;

    public OrderRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    private static final RowMapper<Order> rowMapper = (row, rowNumber) -> Order.builder()
            .orderId(row.getLong("order_id"))
            .userId(row.getLong("user_id"))
            .quantity(row.getInt("quantity"))
            .listOfOrderedPizza(row.getString("list_of_ordered_pizza"))
            .orderCost(row.getInt("order_cost"))
            .dateOfOrder(row.getString("date_of_order"))
            .build();

    @Override
    public Optional<Order> findById(Long id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, rowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Order> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL_PIZZA, rowMapper);
    }

    @Override
    public Order save(Order item) {
        if(item.getOrderId() == null) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement statement = connection.prepareStatement(SQL_INSERT, new String[]{"order_id"});
                statement.setLong(1,item.getUserId());
                statement.setInt(2, item.getQuantity());
                statement.setString(3, item.getListOfOrderedPizza());
                statement.setDouble(4, item.getOrderCost());
                statement.setString(5, item.getDateOfOrder());
                return statement;
            }, keyHolder);
            if (keyHolder.getKey() != null) {
                item.setOrderId(keyHolder.getKey().longValue());
            }
        } else{
            update(item);
        }
        return item;
    }

    @Override
    public void update(Order item) {
        jdbcTemplate.update(SQL_UPDATE_ORDER,
                item.getOrderId(),
                item.getUserId(),
                item.getListOfOrderedPizza(),
                item.getQuantity(),
                item.getDateOfOrder(),
                item.getDateOfOrder());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE_BY_ID, id);
    }
}
