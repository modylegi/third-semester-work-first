package ru.kpfu.itis.servlets;

import ru.kpfu.itis.dao.base.OrderRepository;
import ru.kpfu.itis.dao.base.PizzaRepository;
import ru.kpfu.itis.dao.base.UserRepository;
import ru.kpfu.itis.models.Order;
import ru.kpfu.itis.models.User;
import ru.kpfu.itis.services.base.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/UserOrdersByIdServlet")
public class UserOrdersByIdServlet extends HttpServlet {
    private PizzaRepository pizzaRepository;
    private UserService userService;
    private UserRepository userRepository;
    private OrderRepository orderRepository;

    @Override
    public void init() throws ServletException {
        super.init();
        this.userRepository = (UserRepository) getServletContext().getAttribute("userRepository");
        this.orderRepository = (OrderRepository) getServletContext().getAttribute("orderRepository");
        this.userService = (UserService) getServletContext().getAttribute("userService");

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        User user = userRepository.findById(Math.toIntExact(id)).orElse(null);
        List<Order> userOrders = userService.getCartProducts(user, orderRepository.findAll());
        request.setAttribute("userOrders", userOrders);
        getServletContext().getRequestDispatcher("/WEB-INF/view/default-user/userOrders.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
