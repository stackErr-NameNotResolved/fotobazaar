package classes.servlets.fragments;

import classes.domain.Cart;
import classes.domain.Order;
import classes.servlets.base.BaseHttpServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DecimalFormat;

@WebServlet(name = "CartServletFragment", urlPatterns = {"/CartServletFragment"})
public class CartServletFragment extends BaseHttpServlet {

    private static DecimalFormat df;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.sendRedirect("index.jsp");
        return;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = Cart.readCartFromCookies(request);
        
        request.setAttribute("orders", cart.getOverview());
        
        response.sendRedirect("pages/cart.jsp");
    }

    public static String generateCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuilder writer = new StringBuilder();

        Cart crt = Cart.readCartFromCookies(request);

        // Test data
//        crt = new Cart();
//        crt.addOrder(new Item(), new Picture(), 2);
//        crt.addOrder(new Item(), new Picture(), 1);
//        crt.addOrder(new Item(), new Picture(), 4);
//        if (crt != null) {
//            response = crt.saveCart(response);
//        }
        
        int ROW_WIDTH = 9;

        if (crt == null) {
            writer.append("<table witdh=\"100%\"><tr><td align=\"center\">");
            writer.append("<i style=\"font-size:50pt;\" class=\"glyphicon glyphicon-shopping-cart\"></i></td></tr>");
            writer.append("<tr><td>" + getLocal(request, "cart.empty") + "</td></tr></table>");
        } else if (crt.getOverview().length == 0) {
            writer.append("<table witdh=\"100%\"><tr><td align=\"center\">");
            writer.append("<i style=\"font-size:50pt;\" class=\"glyphicon glyphicon-shopping-cart\"></i></td></tr>");
            writer.append("<tr><td>" + getLocal(request, "cart.empty") + "</td></tr></table>");
        } else {
            String styletag = " style=\"vertical-align: middle\"";

            writer.append("<table class=\"table table-hover\">");
            writer.append("<thead><tr>"
                    + "<th width=\"46%\">" + getLocal(request, "cart.product") + "</th>"
                    + "<th width=\"" + ROW_WIDTH + "%\">" + getLocal(request, "cart.productp") + "</th>"
                    + "<th width=\"" + ROW_WIDTH + "%\">" + getLocal(request, "cart.fotop") + "</th>"
                    + "<th width=\"" + ROW_WIDTH + "%\">" + getLocal(request, "cart.amount") + "</th>"
                    + "<th width=\"" + ROW_WIDTH + "%\">" + getLocal(request, "cart.total") + "</th>"
                    + "<th width=\"" + ROW_WIDTH + "%\"></th>"
                    + "<th width=\"" + ROW_WIDTH + "%\"></th>"
                    + "</tr></thead>");

            writer.append("<tbody>");

            for (Order o : crt.getOverview()) {

                writer.append("<tr>"
                        + "<td" + styletag + ">" + o.getItem().toString() + " + " + getLocal(request, "cart.foto") + "</td>"
                        + "<td" + styletag + ">€ " + formatDouble(o.getItem().getPrice()) + "</td>"
                        + "<td" + styletag + ">€ " + formatDouble(o.getPicture().getPrice()) + "</td>"
                        + "<td" + styletag + "><input class=\"form-control\" id=\"aantal" + o.getId() + "\" placeholder=\"" + getLocal(request, "cart.amount") + "\" type=\"number\" min=\"1\" value=\"" + o.getAmount() + "\"></input></td>"
                        + "<td class=\"active\" style=\"text-align: right; vertical-align: middle\">€ " + formatDouble(o.getTotalPrice()) + "</td>"
                        + "<td" + styletag + "><a class=\"btn btn-primary\" style=\"font-size: 8pt;\"><i class=\"glyphicon glyphicon-edit\" style=\"color: white;\"></i>   " + getLocal(request, "cart.edit") + "</a></td>"
                        + "<td" + styletag + "><a class=\"btn btn-danger\" style=\"font-size: 8pt;\"><i class=\"glyphicon glyphicon-remove\" style=\"color: white;\"></i>   " + getLocal(request, "cart.delete") + "</a></td>"
                );
            }

            writer.append("</tbody><tfoot>");

            writer.append("<tr><td></td><td></td><td></td>"
                    + "<td>"
                    + "<b>" + getLocal(request, "cart.subtotal") + "</b><br/>" + getLocal(request, "cart.btw") + "<br/><b>" + getLocal(request, "cart.finaltotal") + "</b></td>"
                    + "<td class=\"active\" style=\"text-align: right\">€ " + formatDouble(crt.getTotalPrice())
                    + "<br/>€ " + formatDouble(crt.getBTW(21))
                    + "<br/>€ " + formatDouble(crt.getTotalPriceAndBTW(21))
                    + "</td><td></td><td></td></tr>");

            writer.append("</tfoot></table>");

            writer.append("<br /><br /><div class=\"col-md-9\">" + getLocal(request, "cart.empty") + "</div>");
            writer.append("<div class=\"col-md-3\">");
            writer.append("<div class=\"col-md-2\"></div>");
            //writer.write("<input type=\"submit\"/>");
            writer.append("<a href=\"#\" onclick=\"$(this).closest('form').submit()\"><div class=\"btn btn-info col-md-4\">" + getLocal(request, "cart.save") + "</div></a>");
            writer.append("<div class=\"col-md-1\"></div>");
            writer.append("<a href=\"payment.jsp\"><div class=\"btn btn-info col-md-4\">" + getLocal(request, "cart.pay") + "</div></a>");
            writer.append("</div>");

        }
        return writer.toString();
    }

    private static String formatDouble(double value) {
        if (df == null) {
            df = new DecimalFormat();
            df.applyPattern("0.00");
            df.setGroupingUsed(true);
            df.setGroupingSize(3);
        }

        return df.format(value);
    }
}
