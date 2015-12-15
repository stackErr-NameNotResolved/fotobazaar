/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.domain;

import classes.database.DataRow;
import classes.database.DataTable;
import classes.database.DatabaseConnector;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Bas
 */
public class OrderItem implements Serializable {

    private int id;
    private Picture picture;
    private Item item;
    private int amount;

    private DecimalFormat df;

    public OrderItem(int id, Picture picture, Item item, int amount) {
        this.picture = picture;
        this.item = item;
        this.amount = amount;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public double getTotalPrice() {
        return (this.picture.getPrice() + this.item.getPrice()) * this.amount;
    }

    public String getTotalPriceFormat() {
        return formatDouble(getTotalPrice());
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

    public String generateEditLine() {
        StringBuilder sb = new StringBuilder();
        sb.append("imageCode=").append(this.picture.getCode());
        sb.append("&orderId=").append(this.getId());

        sb.append("&startX=").append(this.picture.getStartX());
        sb.append("&startY=").append(this.picture.getStartY());
        sb.append("&endX=").append(this.picture.getEndX());
        sb.append("&endY=").append(this.picture.getEndY());

        sb.append("&Brightness=").append(this.picture.getBrightness());
        sb.append("&Saturation=").append(this.picture.getSaturation());
        sb.append("&Sepia=").append(this.picture.getSepia());
        sb.append("&Clip=").append(this.picture.getClip());
        sb.append("&Blur=").append(this.picture.getBlur());
        sb.append("&Noise=").append(this.picture.getNoise());
        sb.append("&Hue=").append(this.picture.getHue());

        sb.append("&Update=").append(1);

        return sb.toString();
    }

    /**
     * Gets all the current orders from the database
     *
     * @return All the dbOrders
     */
    public static ArrayList<Order> getAllOrders() {
        ArrayList<Order> result = new ArrayList<>();
        try {
            //Select the orders
            DataTable dbResult = DatabaseConnector.getInstance().executeQuery("SELECT * FROM fotobazaar.Order ORDER BY ORDERDATE DESC");

            //Iterate trough orders
            Iterator<DataRow> iterator = dbResult.iterator();            
            while (iterator.hasNext()) {
                //Take the next row
                DataRow row = iterator.next();

                int id = (int) row.getData("ID");
                int customer_id = (int) row.getData("CUSTOMER_ID");
                Timestamp orderdate = (Timestamp) row.getData("ORDERDATE");                
                boolean isPaid = (int) row.getData("PAID") == 1;
                
                //create the row
                Order order = new Order(id, customer_id, orderdate, isPaid);
                result.add(order);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return result;
    }
}
