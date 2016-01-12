/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.servlets;

import classes.database.DataTable;
import classes.database.DatabaseConnector;
import classes.domain.Customer;
import classes.domain.models.Account;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Bas
 */
@WebServlet(name = "CustomerServlet", urlPatterns = {"/CustomerServlet"})
public class CustomerServlet extends HttpServlet {

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
       
        String initials = request.getParameter("initials");
        String lastname = request.getParameter("lastname");
        String gender = request.getParameter("gender");
        String address = request.getParameter("address");
        String number = request.getParameter("number");
        String zip = request.getParameter("zip");
        String city = request.getParameter("city");
        String country = request.getParameter("country");
        String email = request.getParameter("email");
        
        boolean goBack = false;

        if (initials == null || initials.equals("")) {
            request.getSession().setAttribute("initials_error", "has-error");
            goBack = true;
        }
        if (lastname == null || lastname.equals("")) {
            request.getSession().setAttribute("lastname_error", "has-error");
            goBack = true;
        }
        if (gender == null || gender.equals("")) {
            request.getSession().setAttribute("gender_error", "has-error");
            goBack = true;
        }
        if (address == null || address.equals("")) {
            request.getSession().setAttribute("address_error", "has-error");
            goBack = true;
        }
        if (number == null || number.equals("")) {
            request.getSession().setAttribute("number_error", "has-error");
            goBack = true;
        }
        if (zip == null || zip.equals("")) {
            request.getSession().setAttribute("zip_error", "has-error");
            goBack = true;
        }
        if (city == null || city.equals("")) {
            request.getSession().setAttribute("city_error", "has-error");
            goBack = true;
        }
        if (country == null || country.equals("")) {
            request.getSession().setAttribute("country_error", "has-error");
            goBack = true;
        }
        if (email == null || email.equals("")) {
            request.getSession().setAttribute("email_error", "has-error");
            goBack = true;
        }
        
        Account acc = (Account)request.getSession().getAttribute("account");
        DataTable dt = DatabaseConnector.getInstance().executeQuery("SELECT ID FROM ACCOUNT WHERE USERNAME=?", acc.getUsername());
        int accountId = 0;
        if(dt.containsData())
        {
            accountId = Integer.parseInt(dt.getDataFromRow(0, "ID").toString());
        }
        int gen = gender == null ? 0 : gender.equals("man") ? 0 : 1;
        new Customer(initials, lastname, gen, address, number, zip, city, email, accountId, country).update();

        request.getSession().setAttribute("PaymentPage", goBack ? "payment1.jsp" : "payment2.jsp");
        response.sendRedirect("pages/payment.jsp");
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
