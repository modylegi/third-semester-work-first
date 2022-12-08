package ru.kpfu.itis.servlets;

import ru.kpfu.itis.dao.base.PizzaRepository;
import ru.kpfu.itis.models.Pizza;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Optional;
@MultipartConfig
@WebServlet("/update-pizza")
public class UpdatePizzaServlet extends HttpServlet {
    private PizzaRepository pizzaRepository;

    @Override
    public void init() throws ServletException {
        this.pizzaRepository = (PizzaRepository) getServletContext().getAttribute("pizzaRepository");
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
        Long idOfSavedPizza = (Long) request.getSession(true).getAttribute("idOfSavedPizza");
        Part filePart = request.getPart("file");

        Optional<Pizza> pizza = pizzaRepository.findById(idOfSavedPizza);
        Pizza p = pizza.orElse(null);
        System.out.println(p.getPhotoPath());

        String fileName = filePart.getSubmittedFileName();
        if(filePart == null){
            fileName = p.getPhotoPath();
        }
        for (Part part : request.getParts()) {
            part.write("C:\\Users\\user\\Desktop\\univer\\semester-work-first\\src\\main\\webapp\\storage\\" + fileName);
        }


//        if(filePart == null){
//            fileName = p.getPhotoPath();
//        } else{
//            fileName = filePart.getSubmittedFileName();
//            for(Part part: request.getParts()){
//                part.write("C:\\Users\\user\\Desktop\\univer\\semester-work-first\\src\\main\\webapp\\storage\\"+fileName);
//            }
//        }
        Pizza updatedPizza = Pizza.builder()
                .id(idOfSavedPizza)
                .pizza_name(request.getParameter("pizza-name"))
                .ingredients(request.getParameter("pizza-ingredients"))
                .price(Integer.parseInt(request.getParameter("pizza-price")))
                .photoPath(fileName)
                .build();
        if (updatedPizza.getPizza_name() == null && updatedPizza.getIngredients() == null
                && updatedPizza.getPrice() == 0 && updatedPizza.getPhotoPath() == null){
            pizzaRepository.delete(idOfSavedPizza);
        }
        pizzaRepository.update(updatedPizza);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Long idOfSavedPizza = Long.parseLong(request.getParameter("id"));
        session.setAttribute("idOfSavedPizza",idOfSavedPizza);
        Optional<Pizza> oldPizza = pizzaRepository.findById(idOfSavedPizza);

        request.setAttribute("oldPizza",oldPizza.orElse(null));
        getServletContext().getRequestDispatcher("/WEB-INF/view/admin-user/update.jsp").forward(request, response);

    }
}
