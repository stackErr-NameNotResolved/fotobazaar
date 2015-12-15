/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.servlets;

import classes.domain.Cart;
import classes.domain.OrderItem;
import java.io.IOException;
import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Bas
 */
@WebServlet(name = "CartServlet", urlPatterns = {"/CartServlet"})
public class CartServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("pages/cart.jsp");
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");

        String newAantal = request.getParameter("p_newAantal");
        String id = request.getParameter("p_id");

        int iNewAantal = -1;
        int iId = -1;
        if (!newAantal.equals("")) {
            iNewAantal = Integer.parseInt(newAantal);
        }
        if (!id.equals("")) {
            iId = Integer.parseInt(id.replaceAll("amount", ""));
        }
        
        if(iNewAantal == -1 || iId == -1)
            return;

        Cart cart = Cart.readCartFromCookies(request);
        OrderItem o = cart.getOrder(iId);
        o.setAmount(iNewAantal);
        
        cart.saveCart(request, response);

        JsonObject jo = Json.createObjectBuilder()
                .add("id", o.getId())
                .add("total", o.getTotalPriceFormat()) 
                .add("subtotal", cart.getTotalPriceFormat())
                .add("vat", cart.getBTWFormat(21))
                .add("final_total", cart.getTotalPriceAndBTWFormat(21)) 
                .build();

        response.getWriter().write(jo.toString());
        response.getWriter().flush();
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
