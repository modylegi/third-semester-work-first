package ru.kpfu.itis.servlets;

import ru.kpfu.itis.dao.base.PizzaRepository;
import ru.kpfu.itis.models.Pizza;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/pizzaList")
public class PizzaListServlet extends HttpServlet {
    private PizzaRepository pizzaRepository;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        pizzaRepository = (PizzaRepository) getServletContext().getAttribute("pizzaDao");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pizzas", pizzaRepository.findAll());

        getServletContext().getRequestDispatcher("/pizzaList.jsp").forward(req,resp);
//        getServletContext().getRequestDispatcher("/pizzaList.jsp").forward(req,resp);
    }
}
