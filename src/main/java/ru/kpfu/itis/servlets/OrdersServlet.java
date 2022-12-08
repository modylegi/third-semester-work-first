package ru.kpfu.itis.servlets;

import ru.kpfu.itis.dao.base.OrderRepository;
import ru.kpfu.itis.dao.base.UserRepository;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/OrdersServlet")
public class OrdersServlet extends HttpServlet {

    private OrderRepository orderRepository;
    private UserRepository userRepository;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userRepository = (UserRepository) getServletContext().getAttribute("userRepository");
        orderRepository = (OrderRepository) getServletContext().getAttribute("orderRepository");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("orders", orderRepository.findAll());
//        request.setAttribute("user",userRepository.findById());
        getServletContext().getRequestDispatcher("/WEB-INF/view/admin-user/orders.jsp").forward(request,response);
    }

}
