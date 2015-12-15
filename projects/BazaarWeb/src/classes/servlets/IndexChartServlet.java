/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.servlets;

import classes.database.DataTable;
import classes.database.DatabaseConnector;
import classes.domain.IndexChartItem;
import classes.domain.Item;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jip
 */
@WebServlet(name = "IndexChartServlet", urlPatterns = {"/IndexChartServlet"})
public class IndexChartServlet extends HttpServlet {

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
            out.println("<title>Servlet IndexChartServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet IndexChartServlet at " + request.getContextPath() + "</h1>");
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
        int orderId = 1;
        DataTable results = DatabaseConnector.getInstance().executeQuery("SELECT ITEM_ID,AMOUNT,PHOTO_ID FROM item_per_order WHERE ORDER_ID = ?", orderId);

        ArrayList<IndexChartItem> items = new ArrayList<>();

        while (true) {
            Object[] row = results.getNextRow();
            if (row.length == 0) {
                break;
            }
            DataTable photoId = DatabaseConnector.getInstance().executeQuery("SELECT CODE FROM PHOTO WHERE ID = ?", row[2]);
            Object[] photoIdArray = photoId.getNextRow();
            DataTable costumerInfo = DatabaseConnector.getInstance().executeQuery("SELECT NAME, ADDRESS, ZIPCODE,CITY,EMAIL FROM CUSTOMER WHERE ID = (SELECT CUSTOMER_ID FROM `ORDER` WHERE ID = ?)", orderId);
            Object[] costumerInfoArray = costumerInfo.getNextRow();

            IndexChartItem tempItem = new IndexChartItem((String) photoIdArray[0], (int) row[0], (int) row[1],(String)costumerInfoArray[0],(String)costumerInfoArray[1],(String)costumerInfoArray[2],(String)costumerInfoArray[3],(String)costumerInfoArray[4]);
            items.add(tempItem);
        }

        request.setAttribute("items", items);
        request.getRequestDispatcher("pages/indexChart.jsp").forward(request, response);
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
