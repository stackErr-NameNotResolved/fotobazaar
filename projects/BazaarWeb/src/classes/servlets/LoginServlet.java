/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.servlets;

import classes.domain.LoginStatus;
import classes.domain.Session;
import classes.domain.models.Account;
import classes.servlets.base.BaseHttpServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 *
 * @author Bas
 */
@MultipartConfig
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends BaseHttpServlet {
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

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
        HttpSession session = request.getSession(false);

        try {
            String username = request.getParameter("Username");
            String password = request.getParameter("Password");
            boolean payment = request.getParameter("payment") != null;

            if (username.equals("") || password.equals("")) {
                request.getSession().setAttribute("login_message", "3");

                if (payment) {
                    session.setAttribute("PaymentPage", "payment1.jsp");
                    response.sendRedirect("pages/payment.jsp");
                    return;
                }
                response.sendRedirect("pages/login.jsp");
                return;
            }

            LoginStatus loginStatus = Account.validateCredentials(username, password);
            switch (loginStatus)
            {
                case SUCCESS:
                    String encrypted = Session.generateSessionData(username, request.getRemoteAddr());
                    session.setAttribute("account", Session.getAccountFromSession(username, encrypted, request.getRemoteAddr()));
                    session.removeAttribute("login_message");

                    // Redirect.
                    String url = ((String) session.getAttribute("redirecturl"));
                    url = url != null && !url.isEmpty() ? url : "index.jsp";

                    if (payment) {
                        session.setAttribute("PaymentPage", "payment1.jsp");
                        response.sendRedirect("pages/payment.jsp");
                        return;
                    }
                    response.sendRedirect(url);
                    break;
                case FAILED:
                    request.getSession().setAttribute("login_message", "1");
                    break;
                case DISABLED:
                    request.getSession().setAttribute("login_message", "2");
                    break;
            }
            
            if (payment) {
                session.setAttribute("PaymentPage", "payment1.jsp");
                response.sendRedirect("pages/payment.jsp");
            }
        } finally {
            // Clear redirect data.
            session.setAttribute("redirecturl", null);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
