package ru.kpfu.itis.servlets;

import ru.kpfu.itis.dao.base.OrderRepository;
import ru.kpfu.itis.dao.base.UserRepository;
import ru.kpfu.itis.dto.UserDto;
import ru.kpfu.itis.models.User;
import ru.kpfu.itis.services.base.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

import static javax.swing.text.html.CSS.getAttribute;

@WebServlet(value = "/UserDetailsServlet")
public class UserDetailsServlet extends HttpServlet {

    private UserService userService;
    private OrderRepository orderRepository;
    private UserRepository userRepository;

    @Override
    public void init() throws ServletException {
        super.init();
        this.userRepository = (UserRepository) getServletContext().getAttribute("userRepository");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("users",userRepository.findAll());
//        String action = request.getParameter("action");
//        int id = Integer.parseInt(request.getParameter("id"));
//        List<User> ccc = userRepository.findAll();
//        for(User c:ccc){
//            userService.getCartProducts(c, orderRepository.findAll());
//
//        }
        getServletContext().getRequestDispatcher("/WEB-INF/view/admin-user/users.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
