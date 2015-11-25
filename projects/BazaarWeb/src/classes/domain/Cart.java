/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.domain;

import classes.database.DataTable;
import classes.database.DatabaseConnector;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bas
 */
public class Cart implements Serializable {

    private List<Order> orders;
    private int orderId;

    private DecimalFormat df;

    public Cart() {
        orders = new ArrayList();
        orderId = 0;
    }

    public void addOrder(Item item, Picture picture, int aantal) {
        orders.add(new Order(orderId, picture, item, aantal));
        orderId++;
    }

    public void removeOrder(int id) {
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getId() == id) {
                orders.remove(orders.get(i));
            }
        }
    }

    public void updateAmount(int id, int newAmount) {
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getId() == id) {
                orders.get(i).setAmount(newAmount);
            }
        }
    }

    public Order[] getOverview() {
        Order[] array = new Order[orders.size()];
        return orders.toArray(array);
    }

    public double getTotalPrice() {
        double total = 0;
        for (Order order : orders) {
            total += order.getTotalPrice();
        }

        return total;
    }

    public String getTotalPriceFormat() {
        return formatDouble(getTotalPrice());
    }

    public double getBTW(double percentage) {
        return getTotalPrice() * (percentage / 100);
    }

    public String getBTWFormat(double percentage) {
        return formatDouble(getBTW(percentage));
    }

    public double getTotalPriceAndBTW(double percentage) {
        return getTotalPrice() + getBTW(percentage);
    }

    public String getTotalPriceAndBTWFormat(double percentage) {
        return formatDouble(getTotalPriceAndBTW(percentage));
    }

    public static Cart readCartFromCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        List<Cookie> usable = new ArrayList();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().startsWith("order") && !c.getValue().equals("")) {
                    usable.add(c);
                }
            }
        }

        if (!usable.isEmpty()) {
            Cart cart = new Cart();

            // Recreate the orders
            for (Cookie u : usable) {
                String data = u.getValue();

                String[] parameters = data.split("/");
                Item item = null;
                int amount = 0;
                Picture pic = new Picture();
                for (int i = 0; i < parameters.length; i++) {
                    String[] values = parameters[i].split("=");

                    switch (values[0]) {
                        case "i":
                            item = Item.getItemFromId(Integer.parseInt(values[1]));
                            break;
                        case "a":
                            amount = Integer.parseInt(values[1]);
                            break;
                        case "id":
                            pic.setId(Integer.parseInt(values[1]));
                            break;
                        case "sx":
                            pic.setStartX(Float.parseFloat(values[1]));
                            break;
                        case "sy":
                            pic.setStartY(Float.parseFloat(values[1]));
                            break;
                        case "ex":
                            pic.setEndX(Float.parseFloat(values[1]));
                            break;
                        case "ey":
                            pic.setEndY(Float.parseFloat(values[1]));
                            break;
                        case "b":
                            pic.setBrightness(Integer.parseInt(values[1]));
                            break;
                        case "s":
                            pic.setSepia(Integer.parseInt(values[1]));
                            break;
                        case "n":
                            pic.setNoise(Integer.parseInt(values[1]));
                            break;
                        case "l":
                            pic.setBlur(Integer.parseInt(values[1]));
                            break;
                        case "t":
                            pic.setSaturation(Integer.parseInt(values[1]));
                            break;
                        case "h":
                            pic.setHue(Integer.parseInt(values[1]));
                            break;
                        case "c":
                            pic.setClip(Integer.parseInt(values[1]));
                            break;
                    }
                }

                if (pic.getId() != 0) {
                    DataTable dt = DatabaseConnector.getInstance().executeQuery("select price, code from photo where id=?", pic.getId());
                    if (dt.getRowCount() != 0) {
                        pic.setPrice(((BigDecimal) dt.getDataFromRow(0, "price")).doubleValue());
                        pic.setCode((String)dt.getDataFromRow(0, "code"));
                    }
                }

                cart.addOrder(item, pic, amount);
            }

            return cart;
        }

        return new Cart();
    }

    private String formatDouble(double value) {
        if (df == null) {
            df = new DecimalFormat();
            df.applyPattern("0.00");
            df.setGroupingUsed(true);
            df.setGroupingSize(3);
        }

        return df.format(value);
    }

    public HttpServletResponse saveCart(HttpServletRequest request, HttpServletResponse response) {

        // Write the orders
        for (int i = 0; i < orders.size(); i++) {
            StringBuilder sb = new StringBuilder();

            sb.append("i=").append(orders.get(i).getItem().getId()).append("/");
            sb.append(orders.get(i).getPicture().getCookieData());
            sb.append("a=").append(orders.get(i).getAmount());

            Cookie cookie = getCookie("order" + i, request);
            if (cookie == null) {
                cookie = new Cookie("order" + i, "");
            }
            cookie.setValue(sb.toString());
            cookie.setMaxAge(60 * 60 * 24 * 365 * 10);
            cookie.setPath("");

            response.addCookie(cookie);
        }

        Cookie[] cookies = request.getCookies();
        if (cookies.length > orders.size()) {
            for (int i = orders.size(); i < cookies.length; i++) {
                if (cookies[i].getName().startsWith("order")) {
                    cookies[i].setValue("");
                    response.addCookie(cookies[i]);
                }
            }
        }

        return response;
    }
    
    public Order getOrder(int id)
    {
        return this.orders.get(id);
    }

    private Cookie getCookie(String name, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals(name)) {
                return cookies[i];
            }
        }

        return null;
    }

    public static HttpServletResponse addItemToCart(Item item, Picture picture, HttpServletRequest request, HttpServletResponse response) {
        Cart cart = Cart.readCartFromCookies(request);
        if (cart == null) {
            cart = new Cart();
        }
        cart.addOrder(item, picture, 1);
        return cart.saveCart(request, response);
    }

    public static HttpServletResponse addItemToCart(int itemId, Picture picture, HttpServletRequest request, HttpServletResponse response) {
        return addItemToCart(Item.getItemFromId(itemId), picture, request, response);
    }
}
