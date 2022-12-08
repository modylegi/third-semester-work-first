package ru.kpfu.itis.servlets;

import ru.kpfu.itis.dao.base.OrderRepository;
import ru.kpfu.itis.dao.base.UserRepository;
import ru.kpfu.itis.models.User;
import ru.kpfu.itis.services.base.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/GetUserByIdServlet")
public class GetUserByIdServlet extends HttpServlet {
    private UserRepository userRepository;


    @Override
    public void init() throws ServletException {
        super.init();
        this.userRepository = (UserRepository) getServletContext().getAttribute("userRepository");

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        User userInfo = userRepository.findById(Math.toIntExact(id)).orElse(null);
        request.setAttribute("userInfo",userInfo);
        getServletContext().getRequestDispatcher("/WEB-INF/view/admin-user/user.jsp").forward(request,response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
