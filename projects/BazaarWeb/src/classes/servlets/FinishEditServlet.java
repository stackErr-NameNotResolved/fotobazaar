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
        int orderId = Integer.parseInt(request.getParameter("OrderId"));


        int brightness = Integer.parseInt(request.getParameter("Brightness"));
        int saturation = Integer.parseInt(request.getParameter("Saturation"));
        int sepia = Integer.parseInt(request.getParameter("Sepia"));
        int clip = Integer.parseInt(request.getParameter("Clip"));
        int blur = Integer.parseInt(request.getParameter("Blur"));
        int noise = Integer.parseInt(request.getParameter("Noise"));
        int hue = Integer.parseInt(request.getParameter("Hue"));

        float pictureX = Float.parseFloat(request.getParameter("pictureX"));
        float pictureY = Float.parseFloat(request.getParameter("pictureY"));
        float pictureWidth = Float.parseFloat(request.getParameter("pictureWidth"));
        float pictureHeight = Float.parseFloat(request.getParameter("pictureHeight"));

        boolean update = (request.getParameter("Update") != null);

        Picture p = new Picture(ImageCode,
                pictureX,
                pictureY,
                pictureWidth,
                pictureHeight,
                brightness,
                sepia,
                noise,
                blur,
                saturation,
                hue,
                clip);
        
        if (update) {
            Cart.updateItemToCart(orderId, p, request, response);
        } else {
            Cart.addItemToCart(1, p, request, response);
        }

    }

}
