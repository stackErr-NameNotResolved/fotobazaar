/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.servlets;

import classes.domain.Picture;
import classes.domain.models.Account;
import classes.servlets.base.BaseHttpServlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Jip
 */
@WebServlet(name = "ShowPictureServlet", urlPatterns = {"/ShowPictureServlet"})
public class ShowPictureServlet extends BaseHttpServlet {

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
        if (request.getParameter("imageCode") != null) {
            String imageCode = request.getParameter("imageCode");
            String imageSize = request.getParameter("imageSize");
            if (imageSize == null) {
                imageSize = "small";
            }

            if (Picture.isPicturePublished(imageCode)) {
                boolean admin = false;
                if (getSession(request).getAttribute("account")!=null) {
                    Account tempAccount = (Account)getSession(request).getAttribute("account");
                    if (tempAccount.getRight()==1) {
                        admin = true;
                    }
                }
                
                byte[] image = Picture.downloadImage(imageCode, imageSize, admin);
                if (image != null) {
                    response.getOutputStream().write(image);
                }
            }
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
        processRequest(request, response);
        if (request.getParameter("imageCode") != null) {
            String imageCode = request.getParameter("imageCode");

            if (Picture.isPicturePublished(imageCode)) {
                response.sendRedirect(request.getContextPath() + "/pages/pictureView.jsp?imageCode=" + imageCode);
            } else {
                request.setAttribute("visibility", "visible");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
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
