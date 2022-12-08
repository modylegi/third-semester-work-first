package ru.kpfu.itis.servlets;

import ru.kpfu.itis.dto.UserForm;
import ru.kpfu.itis.exceptions.ValidationException;
import ru.kpfu.itis.services.base.SignUpService;
import ru.kpfu.itis.services.validation.ErrorEntity;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {

    private SignUpService signUpService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        this.signUpService = (SignUpService) context.getAttribute("signUpService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/view/security/signUp.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserForm form;
        try {
            form = UserForm.builder()
                    .email(request.getParameter("email"))
                    .password(request.getParameter("password"))
                    .build();
        } catch (NumberFormatException e) {
            Set<ErrorEntity> errors = new HashSet<>();
            errors.add(ErrorEntity.INVALID_REQUEST);
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("WEB-INF/view/security/signUp.jsp").forward(request, response);
            return;
        }

        try {
            signUpService.signUp(form);
        } catch (ValidationException e) {
            request.setAttribute("error", e.getEntity());
            request.getRequestDispatcher("WEB-INF/view/security/signUp.jsp").forward(request, response);
            return;
        }
        response.sendRedirect("signIn");
    }
}
