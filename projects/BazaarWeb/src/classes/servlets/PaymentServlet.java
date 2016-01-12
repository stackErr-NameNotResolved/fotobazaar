/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.servlets;

import classes.database.DataTable;
import classes.database.DatabaseConnector;
import classes.database.StatementResult;
import classes.domain.*;
import classes.domain.models.Account;
import classes.servlets.base.BaseHttpServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Baya
 */
@WebServlet(name = "PaymentServlet", urlPatterns = {"/PaymentServlet"})
public class PaymentServlet extends BaseHttpServlet {

    BankAccount testBank;

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
            out.println("<title>Servlet Payment</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Payment at " + request.getContextPath() + "</h1>");
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

        HttpSession session = request.getSession(false);
        testBank = new BankAccount("veel", "geld", 100.00);
        String bankFlow = String.valueOf(session.getAttribute("bankFlow"));

        switch (bankFlow) {
            case "choice":
                session.setAttribute("bankFlow", "login");
                response.sendRedirect("pages/paymentService.jsp");
                break;
            case "login":
                if (request.getParameter("username").equals(testBank.getUsername()) && testBank.checkPassword(request.getParameter("password"))) {
                    session.setAttribute("bankFlow", "pay");
                    session.setAttribute("login_message", 0);
                    response.sendRedirect("pages/paymentService.jsp");
                } else {
                    session.setAttribute("login_message", 1);
                    response.sendRedirect("pages/paymentService.jsp");
                }
                break;
            case "pay":
                NumberFormat format = NumberFormat.getInstance();
                Number number = 0;
                try {
                    number = format.parse(request.getParameter("amount"));
                } catch (ParseException ex) {
                    Logger.getLogger(PaymentServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                double amount = number.doubleValue();
                if (amount != 0 && testBank.checkBalance(amount)) {
                    System.out.println(testBank.getBalance());
                    testBank.pay(amount);
                    System.out.println(testBank.getBalance());
                    session.setAttribute("bankFlow", "choice");
                    session.setAttribute("payment_message", 0);
                    Cart cart = Cart.readCartFromCookies(request);
                    OrderItem[] test = cart.getOverview();
                    Account account = (Account) getSession(request).getAttribute("account");
                    Customer customer =  Customer.fromAccountId(account.getId());
                    Order order = new Order(0, customer.getId(), null, true, false);
                    StatementResult dbResult = DatabaseConnector.getInstance().executeNonQuery("INSERT INTO `order` (CUSTOMER_ID, PAID, DONE, ORDERDATE) VALUES (?,?,?,NOW())", order.getCustomer_id(), order.isPaid(), order.isDone());
                    DataTable dt = DatabaseConnector.getInstance().executeQuery("SELECT MAX(id) as lastID FROM `order`");
                    if (dt.getRowCount() == 1) {
                        int orderId = (int)dt.getDataFromRow(0, "lastID");
                        OrderItem[] items = cart.getOverview();
                        for(int i=0; i<items.length; i++) {
                            System.out.println(items[i].getId());
                            dbResult = DatabaseConnector.getInstance().executeNonQuery("INSERT INTO item_per_order (ORDER_ID, ITEM_ID, AMOUNT, PHOTO_ID, START_X, START_Y, END_X, END_Y, BRIGHTNESS, SEPIA, NOISE, BLUR, SATURATION, HUE, CLIP) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                                    orderId, items[i].getItem().getId(), items[i].getAmount(), items[i].getPicture().getId(), items[i].getPicture().getStartX(), items[i].getPicture().getStartY(), items[i].getPicture().getEndX(), items[i].getPicture().getEndY(), items[i].getPicture().getBrightness(), items[i].getPicture().getSepia(), items[i].getPicture().getNoise(), items[i].getPicture().getBlur(), items[i].getPicture().getSaturation(), items[i].getPicture().getHue(), items[i].getPicture().getClip());
                        }
                    }
                    if (!dbResult.equals(StatementResult.ERROR) || !dbResult.equals(StatementResult.NO_ROWS_UPDATED)) {
                        cart.clearCart();
                        cart.saveCart(request, response);
                        response.sendRedirect("pages/paymentSucces.jsp");
                    } else {
                        session.setAttribute("bankFlow", "choice");
                        session.setAttribute("payment_message", 1);
                        response.sendRedirect("pages/payment.jsp");
                    }
                } else {
                    session.setAttribute("bankFlow", "choice");
                    session.setAttribute("payment_message", 1);
                    response.sendRedirect("pages/payment.jsp");
                }
                break;
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
