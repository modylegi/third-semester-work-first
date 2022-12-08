package ru.kpfu.itis.servlets;


import ru.kpfu.itis.dto.UserDto;
import ru.kpfu.itis.dto.UserForm;
import ru.kpfu.itis.exceptions.ValidationException;

import ru.kpfu.itis.services.base.SignInService;


import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/signIn")
public class SignInServlet extends HttpServlet {
    private SignInService signInService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        this.signInService = (SignInService) context.getAttribute("signInService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/view/security/signIn.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserForm form = UserForm.builder()
                .email(request.getParameter("email"))
                .password(request.getParameter("password"))

                .build();
        UserDto userDto;

        try {
            userDto = signInService.signIn(form);
        } catch (ValidationException e) {
            response.sendRedirect("signIn");
            return;
        }

        response.addCookie(new Cookie("token", userDto.getToken()));
        HttpSession session = request.getSession(true);
        session.setAttribute("user", userDto);
        response.sendRedirect("pizzaList");
    }

}