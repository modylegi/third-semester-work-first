package ru.kpfu.itis.services.base;

import ru.kpfu.itis.models.Cart;

import java.util.List;

public interface CartProducts {
    List<Cart> getCartProducts(List<Cart> cartList);
    double getTotalCartPrice(List<Cart> cartList);
    int getTotalCartItems(List<Cart> cartList);
}
