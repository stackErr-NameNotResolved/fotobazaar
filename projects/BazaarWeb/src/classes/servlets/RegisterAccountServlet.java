/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.servlets;

import classes.domain.Session;
import classes.domain.models.Account;
import classes.servlets.base.BaseHttpServlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author sjorsvanmierlo
 */
@MultipartConfig
@WebServlet(name = "RegisterAccountServlet", urlPatterns = {"/RegisterAccountServlet"})
public class RegisterAccountServlet extends BaseHttpServlet {

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
        String username = request.getParameter("username");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");
        String redirect = request.getParameter("redirect");

        if (!username.isEmpty() || password1.isEmpty() || password2.isEmpty()) {

            if (password1.equals(password2)) {
            Account.registerNewAccount(username, password2, Account.Rights.Customer);

                HttpSession session = request.getSession(false);
                String encrypted = Session.generateSessionData(username, request.getRemoteAddr());
                session.setAttribute("account", Session.getAccountFromSession(username, encrypted, request.getRemoteAddr()));

                if (redirect.equals("true")) {
                    session.setAttribute("PaymentPage", "payment1.jsp");
                    response.sendRedirect("pages/payment.jsp");
                    return;
                }

                response.sendRedirect("pages/login.jsp");
                return;
            }
        }
        response.sendRedirect("pages/registration.jsp");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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

    }

}
