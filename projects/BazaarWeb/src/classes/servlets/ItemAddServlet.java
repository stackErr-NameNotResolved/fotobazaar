/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.servlets;

import classes.domain.Item;
import classes.servlets.base.BaseHttpServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collection;

/**
 *
 * @author Jip
 */
@MultipartConfig
@WebServlet(name = "ItemAddServlet", urlPatterns = {"/ItemAddServlet"})
public class ItemAddServlet extends BaseHttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ItemAddServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ItemAddServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        response.sendRedirect("pages/itemAdd.jsp");
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
        try {
            if (request.getPart("itemPrice") != null && request.getPart("itemDescription") != null && request.getParts() != null) {
                Double price = Double.valueOf(getValueFromPart(request.getPart("itemPrice")));
                String description = getValueFromPart(request.getPart("itemDescription"));
                boolean active = false;

                if (request.getPart("active") != null) {
                    active = true;
                }

                Collection<Part> filePart = request.getParts(); // Retrieves <input type="file" name="file">
                Part image = null;

                for (Part part : filePart) {
                    if (part.getContentType() != null) {//true if is image
                        if (part.getSize() != 0) {
                            image = part;
                        }
                    }
                }

                if (image != null) {
                    Item.create(description, price, active, image);
                }

                response.sendRedirect("ItemViewServlet");
            }

        } catch (IOException | ServletException | NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Will return the String content of a part.
     *
     * @param part servlet response
     * @throws IOException if an I/O error occurs
     * @return String value of the text property
     */
    private static String getValueFromPart(Part part) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(part.getInputStream(), "UTF-8"));
        StringBuilder value = new StringBuilder();
        char[] buffer = new char[1024];
        for (int length = 0; (length = reader.read(buffer)) > 0;) {
            value.append(buffer, 0, length);
        }
        return value.toString();
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
