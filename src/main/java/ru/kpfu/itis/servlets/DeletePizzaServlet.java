package ru.kpfu.itis.servlets;

import ru.kpfu.itis.dao.base.PizzaRepository;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/DeletePizzaServlet")
public class DeletePizzaServlet extends HttpServlet {
    private PizzaRepository pizzaRepository;

    @Override
    public void init() throws ServletException {
        this.pizzaRepository = (PizzaRepository) getServletContext().getAttribute("pizzaRepository");
        super.init();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long idOfDeletePizza = Long.parseLong(request.getParameter("id"));
        pizzaRepository.delete(idOfDeletePizza);
        response.sendRedirect("pizzaList");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
