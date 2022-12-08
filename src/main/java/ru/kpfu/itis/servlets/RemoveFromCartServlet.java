package ru.kpfu.itis.servlets;

import ru.kpfu.itis.models.Cart;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/RemoveFromCartServlet")
public class RemoveFromCartServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id != null){
            List<Cart> cart_list = (List<Cart>) request.getSession().getAttribute("cart-list");
            if(cart_list != null){
                for(Cart c: cart_list){
                    if(c.getId() == Integer.parseInt(id)){
                        cart_list.remove(cart_list.indexOf(c));
                        break;
                    }
                }
                response.sendRedirect("cart");
            }

        } else{
            response.sendRedirect("cart");
        }

    }
}
