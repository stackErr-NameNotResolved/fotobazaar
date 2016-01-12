package classes.servlets;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import classes.domain.Picture;
import classes.domain.models.Account;
import classes.servlets.base.BaseHttpServlet;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Jip
 */
@MultipartConfig
@WebServlet(urlPatterns = {"/PhotoUploadServlet"})
public class PhotoUploadServlet extends BaseHttpServlet {

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
            out.println("<title>Servlet fotoUpload</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet fotoUpload at " + request.getContextPath() + "</h1>");
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
        boolean succes = false;//if images are uploaded correctly
        boolean singlePartNotFail = true;//if one image did fail

        try {
            if (request.getPart("PicturePrice") != null && request.getParts() != null) {
                String value = getValueFromPart(request.getPart("PicturePrice"));
                Double price = Double.parseDouble(value);
                Collection<Part> filePart = request.getParts(); // Retrieves <input type="file" name="file">                

                for (Part part : filePart) {
                    if (part.getContentType() != null) {//true if is image
                        if (part.getSize() != 0L) {
                            Account tempAccount = (Account)getSession(request).getAttribute("account");
                            succes = Picture.uploadPicture(part, tempAccount.getPhotographer().getId(), price, 200);//will return false if failed

                            if (!succes) {
                                singlePartNotFail = false;
                            }
                        }
                    }
                }
            }

        } catch (IOException | ServletException | NumberFormatException e) {
            System.out.println(e.getMessage());
        }

        if (!singlePartNotFail) {
            succes = false;
        }

        getSession(request).setAttribute("status", succes);
        response.sendRedirect("pages/pictureUpload.jsp");
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
