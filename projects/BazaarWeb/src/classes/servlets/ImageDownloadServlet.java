/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.servlets;

import classes.domain.Picture;
import classes.servlets.base.BaseHttpServlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sjorsvanmierlo
 */
@WebServlet(name = "ImageDownloadServlet", urlPatterns = {"/ImageDownloadServlet"})
public class ImageDownloadServlet extends BaseHttpServlet {

 

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
     
    if (request.getParameter("imageCode") != null) {
            String imageCode = request.getParameter("imageCode");
            String imageSize = request.getParameter("imageSize");
            if (imageSize == null) {
                imageSize = "small";
            }
            
            if (Picture.isPicturePublished(imageCode)) {
                byte[] image = Picture.downloadImage(imageCode, imageSize);
                if (image != null) {
                    response.setContentType("Image/JPG");
                    response.setHeader("Content-disposition", "attachment; filename="+ imageCode +".jpg");
                    response.getOutputStream().write(image);
                }
            }
        }
    }
}
