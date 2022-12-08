package ru.kpfu.itis.servlets;

import ru.kpfu.itis.dao.base.PizzaRepository;

import ru.kpfu.itis.models.Pizza;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@MultipartConfig
@WebServlet("/addPizza")
public class AddPizzaServlet extends HttpServlet {

    private PizzaRepository pizzaRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        pizzaRepository = (PizzaRepository) config.getServletContext().getAttribute("pizzaRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/view/admin-user/create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part filePart = req.getPart("file");
        String fileName = filePart.getSubmittedFileName();
        for(Part part: req.getParts()){
            part.write("C:\\Users\\user\\Desktop\\univer\\semester-work-first\\src\\main\\webapp\\storage\\"+fileName);
        }
        Pizza form = Pizza.builder()
                .pizza_name(req.getParameter("pizza-name"))
                .ingredients(req.getParameter("pizza-ingredients"))
                .price(Integer.parseInt(req.getParameter("pizza-price")))
                .photoPath(fileName)
                .build();
        System.out.println(form);
        pizzaRepository.save(form);

    }
}
