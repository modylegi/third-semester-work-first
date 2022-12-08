package ru.kpfu.itis.services.base;

import ru.kpfu.itis.dto.UserDto;
import ru.kpfu.itis.models.Order;
import ru.kpfu.itis.models.User;

import java.util.List;

public interface UserService {
    List<Order> getCartProducts(UserDto user, List<Order> item);
    List<Order> getCartProducts(User user, List<Order> item);
}
