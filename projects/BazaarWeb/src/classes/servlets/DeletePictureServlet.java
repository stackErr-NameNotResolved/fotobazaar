/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.servlets;

import classes.domain.Picture;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author sjorsvanmierlo
 */
@MultipartConfig
@WebServlet(urlPatterns = {"/DeletePictureServlet"})
public class DeletePictureServlet extends HttpServlet {

    protected void proccesRequest(HttpServletRequest request, HttpServletResponse response, boolean actionResult) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String photoId = request.getParameter("photoId");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet delete picture servlet</title>");
            out.println("</head>");
            out.println("<body>");
            if (actionResult) {
                out.println("<h1>Picture " + photoId + " deleted!</h1>");
            } else {
                out.println("<h1>Picture " + photoId + " NOT deleted!</h1>");
            }

            out.println("</body>");
            out.println("</html>");
        }

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

        //Retrieve photo ID from the page
        String photoIDValue = request.getParameter("photoId");
        int photoId = Integer.parseInt(photoIDValue);

        //Let it process the request
        proccesRequest(request, response, Picture.deletePicture(photoId));

    }
}
