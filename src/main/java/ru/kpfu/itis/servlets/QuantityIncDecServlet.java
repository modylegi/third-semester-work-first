package ru.kpfu.itis.servlets;

import ru.kpfu.itis.models.Cart;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/quantityIncDecServlet")
public class QuantityIncDecServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String action = request.getParameter("action");
            int id = Integer.parseInt(request.getParameter("id"));
            ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");

            if (action != null && id >= 1) {
                if (action.equals("inc")) {
                    for (Cart c : cart_list) {
                        if (c.getId() == id) {
                            int quantity = c.getQuantity();
                            quantity++;
                            out.println(quantity);
                            System.out.println(quantity);
                            c.setQuantity(quantity);
                            response.sendRedirect("cart");
                        }
                    }
                }

                if (action.equals("dec")) {
                    for (Cart c : cart_list) {
                        if (c.getId() == id && c.getQuantity() > 1) {
                            int quantity = c.getQuantity();
                            quantity--;
                            out.println(quantity);
                            System.out.println(quantity);
                            c.setQuantity(quantity);
                            break;
                        }
                    }
                    response.sendRedirect("cart");
                }
            } else {
                response.sendRedirect("cart");
            }
        }
    }
}
