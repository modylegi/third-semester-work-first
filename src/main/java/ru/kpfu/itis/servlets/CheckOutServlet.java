package ru.kpfu.itis.servlets;

import ru.kpfu.itis.dao.base.OrderRepository;
import ru.kpfu.itis.dto.UserDto;
import ru.kpfu.itis.models.Cart;
import ru.kpfu.itis.models.Order;
import ru.kpfu.itis.services.base.CartProducts;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/CheckOutServlet")
public class CheckOutServlet extends HttpServlet {

    private CartProducts cartProducts;
    private OrderRepository orderRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.cartProducts = (CartProducts) getServletContext().getAttribute("cartProducts");
        this.orderRepository = (OrderRepository) getServletContext().getAttribute("orderRepository");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        List<Cart> cart_list = (List<Cart>) request.getSession().getAttribute("cart-list");
        UserDto user = (UserDto) request.getSession(true).getAttribute("user");
        List<String> listOfOrderedPizza = new ArrayList<>();
        if(cart_list != null && user != null) {
            List<Cart> cartProduct = cartProducts.getCartProducts(cart_list);
            for (Cart c : cartProduct) {
                listOfOrderedPizza.add(c.getPizza_name());
            }
            String StringOfOrderedPizza = listOfOrderedPizza.stream().map(Object::toString)
                    .collect(Collectors.joining(", "));
            Order order = new Order();
            order.setUserId(user.getId());
            order.setListOfOrderedPizza(StringOfOrderedPizza);
            order.setQuantity(cartProducts.getTotalCartItems(cart_list));
            order.setOrderCost(cartProducts.getTotalCartPrice(cart_list));
            order.setDateOfOrder(sdf.format(date));
            orderRepository.save(order);
            cart_list.clear();
            response.sendRedirect("cart");

        } else {
            if(user == null){
                response.sendRedirect("signIn");
            }


        }

    }

}
