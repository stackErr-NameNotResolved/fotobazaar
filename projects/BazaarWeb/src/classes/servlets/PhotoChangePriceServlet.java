/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.servlets;

import classes.domain.Picture;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sjorsvanmierlo
 */
@MultipartConfig
@WebServlet(urlPatterns = {"/PhotoChangePriceServlet"})
public class PhotoChangePriceServlet extends HttpServlet {

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

        String photoIDValue = request.getParameter("photoId");
        int photoId = Integer.parseInt(photoIDValue);
        String newPriceValue = request.getParameter("photoPrice");
        double newPrice = Double.parseDouble(newPriceValue);

        Picture.updatePrice(newPrice, photoId);
        response.sendRedirect("PhotoViewServlet");
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @param result
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, boolean result)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet fotoUpload</title>");
            out.println("</head>");
            out.println("<body>");
            if (result) {
                out.println("<h1>Price changed to " + request.getParameter("photoPrice") + " for photoId = " + request.getParameter("photoId") + "</h1>");
            } else {
                out.println("<h1>Price did not update!</h1>");
            }

            out.println("</body>");
            out.println("</html>");
        }
    }
}
