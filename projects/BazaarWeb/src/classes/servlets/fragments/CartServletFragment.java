package classes.servlets.fragments;

import classes.domain.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Cookie;

@WebServlet(name = "CartServletFragment", urlPatterns = {"/CartServletFragment"})
public class CartServletFragment extends HttpServlet {
    
    private DecimalFormat df;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();

        Cookie[] cookies = request.getCookies();
        Cookie currentCart = null;
        for (Cookie c : cookies) {
            if (c.getName().equals("cart")) {
                currentCart = c;
            }
        }

        Cart crt = null;
        try {
            if (currentCart != null) {
                crt = Cart.fromString(currentCart.getValue());
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CartServletFragment.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Test data
        crt = new Cart();
        crt.addOrder(new Item(1), new Picture(), 2);
        crt.addOrder(new Item(1), new Picture(), 1);

        int ROW_WIDTH = 7;

        if (crt == null) {
            writer.write("There are no items in your cart. Add items to your cart to view them here");
        } else {

            writer.write("<table class=\"table table-hover\">");
            writer.write("<thead><tr>"
                    + "<th width=\"45%\">Product</th>"
                    + "<th width=\"" + ROW_WIDTH + "%\">Product prijs</th>"
                    + "<th width=\"" + ROW_WIDTH + "%\">Foto prijs</th>"
                    + "<th width=\"" + ROW_WIDTH + "%\">Aantal</th>"
                    + "<th width=\"" + ROW_WIDTH + "%\">Totaal</th>"
                    + "<th width=\"1%\"></th>"
                    + "</tr></thead>");

            writer.write("<tbody>");

            for (Order o : crt.getOverview()) {
                writer.write("<tr>"
                        + "<td>" + o.getItem().toString() + " + foto</td>"
                        + "<td>€ " + formatDouble(o.getItem().getPrice()) + "</td>"
                        + "<td>€ " + formatDouble(o.getPicture().getPrice()) + "</td>"
                        + "<td><input class=\"form-control\" placeholder=\"Aantal\" type=\"number\" min=\"1\" value=\"" + o.getAmount() + "\"></input></td>"
                        + "<td class=\"active\" style=\"text-align: right;\">€ " + formatDouble(o.getTotalPrice()) + "</td>"
                        + "<td class=\"danger\"><a class=\"btn\"  style=\"font-size: 8pt;\"><i class=\"glyphicon glyphicon-remove\"></i>   Verwijderen</a></td>");
            }

            writer.write("</tbody><tfoot>");

            writer.write("<tr><td></td><td></td><td></td>"
                    + "<td>"
                    + "<b>Subtotaal</b><br/>BTW (21%)<br/><b>Totaal</b></td>"
                    + "<td style=\"text-align: right\">€ " + formatDouble(crt.getTotalPrice()) 
                    + "<br/>€ " + formatDouble(crt.getTotalPrice() * 0.21) 
                    + "<br/>€ " + formatDouble(crt.getTotalPrice() + crt.getTotalPrice() * 0.21)
                    + "</td></tr>");

            writer.write("</tfoot></table>");
        }
    }
    
    private String formatDouble(double value)
    {
        if(df == null)
        {
            df = new DecimalFormat();
            df.applyPattern("#.00");
            df.setGroupingUsed(true);
            df.setGroupingSize(3);
        }
        
        return df.format(value);
    }
}
