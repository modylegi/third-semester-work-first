package ru.kpfu.itis.services.impl;


import ru.kpfu.itis.dto.UserDto;
import ru.kpfu.itis.models.Order;
import ru.kpfu.itis.models.User;
import ru.kpfu.itis.services.base.UserService;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {






    @Override
    public List<Order> getCartProducts(UserDto user, List<Order> item) {
        List<Order> lll = new ArrayList<>();
        for (Order c: item){
            if(c.getUserId() == user.getId()){
                lll.add(c);
            }
        }

        return lll;
    }
    @Override
    public List<Order> getCartProducts(User user, List<Order> item) {
        List<Order> lll = new ArrayList<>();
        for (Order c: item){
            if(c.getUserId() == user.getId()){
                lll.add(c);
            }
        }

        return lll;
    }
}

