package ru.kpfu.itis.servlets;

import ru.kpfu.itis.models.Cart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/add-to-cart")
public class AddPizzaToCartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (PrintWriter out = resp.getWriter()){
            List<Cart> cartList = new ArrayList<>();
            Long id = Long.parseLong(req.getParameter("id"));
            Cart cm = new Cart();
            cm.setId(id);
            cm.setQuantity(1);
            HttpSession session = req.getSession();
            List<Cart> cart_list = (List<Cart>) session.getAttribute("cart-list");
            if(cart_list == null){
                cartList.add(cm);
                session.setAttribute("cart-list", cartList);
                resp.sendRedirect("pizzaList");
            } else {
                cartList = cart_list;
                boolean exist = false;

                for(Cart c:cartList){
                    if(c.getId() == id){
                        exist = true;
                        resp.sendRedirect("cart");
//                        out.println("<h3 style='color:crimson; text-align: center'>Item Already in Cart. <a href='$cart'>GO to Cart Page</a></h3>");
                    }
                }
                if(!exist){
                    cartList.add(cm);
                    resp.sendRedirect("pizzaList");
                }
            }
        }

    }
}
