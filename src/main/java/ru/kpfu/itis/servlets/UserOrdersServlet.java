package ru.kpfu.itis.servlets;

import ru.kpfu.itis.dao.base.OrderRepository;
import ru.kpfu.itis.dto.UserDto;
import ru.kpfu.itis.models.Order;
import ru.kpfu.itis.services.base.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/UserOrders")
public class UserOrdersServlet extends HttpServlet {

    private OrderRepository orderRepository;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();

        this.orderRepository = (OrderRepository) getServletContext().getAttribute("orderRepository");
        this.userService = (UserService) getServletContext().getAttribute("userService");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDto user = (UserDto) request.getSession(true).getAttribute("user");
        List<Order> userOrders = userService.getCartProducts(user, orderRepository.findAll());
        request.setAttribute("userOrders", userOrders);
        getServletContext().getRequestDispatcher("/WEB-INF/view/default-user/userOrders.jsp").forward(request,response);

    }


}
