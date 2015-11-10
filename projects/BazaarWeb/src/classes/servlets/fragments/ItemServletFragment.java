/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.servlets.fragments;

import classes.database.DataRow;
import classes.database.DataTable;
import classes.database.DatabaseConnector;
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
@WebServlet(name = "ItemServletFragment", urlPatterns = {"/ItemServletFragment"})
public class ItemServletFragment extends HttpServlet {

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Item> items = new ArrayList<Item>();
        
        DataTable result = DatabaseConnector.getInstance().executeQuery("SELECT ID FROM item WHERE ACTIVE = 1");
        while (true) {            
            Object[] itemRow = result.getNextRow();
            if (itemRow.length==0) {
                break;
            }
            items.add(new Item((Integer)itemRow[0]));
        }
        
        PrintWriter writer = response.getWriter();
        for (Item item : items) {
            writer.write(item.toString());
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
