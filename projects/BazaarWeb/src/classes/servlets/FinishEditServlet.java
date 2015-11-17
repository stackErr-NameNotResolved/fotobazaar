/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.servlets;

import classes.domain.Cart;
import classes.domain.Picture;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Fatih
 */
@WebServlet(name = "FinishEditServlet", urlPatterns = {"/FinishEditServlet"})
public class FinishEditServlet extends HttpServlet {

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
        }
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

        String ImageCode = request.getParameter("ImageCode");    
        
        int Brightness = Integer.parseInt(request.getParameter("Brightness"));
        int Saturation = Integer.parseInt(request.getParameter("Saturation"));
        int Sepia = Integer.parseInt(request.getParameter("Sepia"));
        int Clip = Integer.parseInt(request.getParameter("Clip"));
        int Blur = Integer.parseInt(request.getParameter("Blur"));
        int Noise = Integer.parseInt(request.getParameter("Noise"));
        int Hue = Integer.parseInt(request.getParameter("Hue"));

        float pictureX = Float.parseFloat(request.getParameter("pictureX"));
        float pictureY = Float.parseFloat(request.getParameter("pictureY"));
        float pictureWidth = Float.parseFloat(request.getParameter("pictureWidth"));
        float pictureHeight = Float.parseFloat(request.getParameter("pictureHeight"));

        Picture p = new Picture(ImageCode,
                pictureX,
                pictureY,
                pictureWidth,
                pictureHeight,
                Brightness,
                Sepia,
                Noise,
                Blur,
                Saturation,
                Hue,
                Clip);
        
        Cart.addItemToCart(1, p, request, response);
    }

}
