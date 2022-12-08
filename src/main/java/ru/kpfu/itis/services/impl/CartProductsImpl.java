package ru.kpfu.itis.services.impl;

import ru.kpfu.itis.dao.base.PizzaRepository;
import ru.kpfu.itis.models.Cart;
import ru.kpfu.itis.models.Pizza;
import ru.kpfu.itis.services.base.CartProducts;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CartProductsImpl implements CartProducts {
    private final PizzaRepository pizzaRepository;

    public CartProductsImpl(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    @Override
    public List<Cart> getCartProducts(List<Cart> cartList) {
        List<Cart> products = new ArrayList<>();
        try{
            if(cartList.size()>0){
                for(Cart item:cartList){
                    Optional<Pizza> i = pizzaRepository.findById(item.getId());
//                    System.out.println(i);
                    Cart row = new Cart();
                    row.setId(i.get().getId());
                    row.setPizza_name(i.get().getPizza_name());
                    row.setIngredients(i.get().getIngredients());
                    row.setPrice(i.get().getPrice());
                    row.setQuantity(item.getQuantity());
                    row.setTotalSum(item.getQuantity()*i.get().getPrice());
                    products.add(row);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return products;
    }

    @Override
    public double getTotalCartPrice(List<Cart> cartList) {
        double TotalCartPrice = 0;
        if (cartList.size() > 0) {
            for(Cart item:cartList){
                Optional<Pizza> i = pizzaRepository.findById(item.getId());
                TotalCartPrice = TotalCartPrice +  i.get().getPrice()*item.getQuantity();
            }
        }
        return TotalCartPrice;
    }

    @Override
    public int getTotalCartItems(List<Cart> cartList) {
        int TotalCartItems = 0;
        if (cartList.size() > 0) {
            for(Cart item:cartList) {
                Optional<Pizza> i = pizzaRepository.findById(item.getId());
                TotalCartItems = TotalCartItems +  item.getQuantity();

            }

        }
        return TotalCartItems;
    }
}
