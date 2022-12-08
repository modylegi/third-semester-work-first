package ru.kpfu.itis.servlets;

//import ru.kpfu.itis.models.Cart;
import ru.kpfu.itis.models.Cart;
        import ru.kpfu.itis.services.base.CartProducts;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
        import java.util.List;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    private CartProducts cartProducts;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.cartProducts = (CartProducts) getServletContext().getAttribute("cartProducts");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cart> cart_list = (List<Cart>) request.getSession(true).getAttribute("cart-list");
        List<Cart> cartProduct = null;
        if (cart_list != null) {
            cartProduct = cartProducts.getCartProducts(cart_list);
            double totalCartPrice = cartProducts.getTotalCartPrice(cart_list);
            int totalCartItems = cartProducts.getTotalCartItems(cart_list);
            request.setAttribute("cartProduct", cartProduct);
            request.setAttribute("totalCartPrice", totalCartPrice);
            request.setAttribute("totalCartItems", totalCartItems);

        }
        getServletContext().getRequestDispatcher("/WEB-INF/view/default-user/cart.jsp").forward(request, response);

    }
}
