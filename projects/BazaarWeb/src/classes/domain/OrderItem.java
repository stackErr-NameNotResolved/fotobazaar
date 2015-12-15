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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Bas
 */
public class OrderItem implements Serializable {

    private int orderId;
    private Picture picture;
    private Item item;
    private int amount;

    private DecimalFormat df;

    public OrderItem(){}
    
    public OrderItem(int orderId, Picture picture, Item item, int amount) {
        this.picture = picture;
        this.item = item;
        this.amount = amount;
        this.orderId = orderId;
    }
    
     public OrderItem(int orderId, int itemId, int itemAmount, int photoId, int startX, int startY, int endX, int endY, int brightness, int sepia, int noise, int blur, int saturation, int hue, int clip) {
        this.orderId = orderId;        
        this.item = Item.getItemFromId(itemId);
        this.amount = itemAmount;        
        this.picture = new Picture(startX, startY, endX, endY, brightness, sepia, noise, blur, saturation, hue, clip);
        this.picture.setId(photoId);
    }

    public int getId() {
        return orderId;
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
     * Get all the items and picture for the specified order
     * @param orderId The order id
     * @return List with items
     */
    public static ArrayList<OrderItem> getItemsForOrder(int orderId){
        ArrayList<OrderItem> result = new ArrayList<>();
        
         try {
            //Select the orders
            DataTable dbResult = DatabaseConnector.getInstance().executeQuery("SELECT * FROM fotobazaar.item_per_order WHERE ORDER_ID = ?", orderId);

            //Iterate trough orders
            Iterator<DataRow> iterator = dbResult.iterator();            
            while (iterator.hasNext()) {
                //Take the next row
                DataRow row = iterator.next();
                 
                int itemId = (int) row.getData("ITEM_ID");        
                int itemAmount = (int) row.getData("AMOUNT");    
                int photoId = (int) row.getData("PHOTO_ID");    
                int startX = (int) row.getData("START_X");    
                int startY = (int) row.getData("START_Y");    
                int endX = (int) row.getData("END_X");    
                int endY = (int) row.getData("END_Y");    
                int brightness = (int) row.getData("BRIGHTNESS");    
                int sepia = (int) row.getData("SEPIA");    
                int noise = (int) row.getData("NOISE");    
                int blur = (int) row.getData("BLUR");    
                int saturation = (int) row.getData("SATURATION");    
                int hue = (int) row.getData("HUE");    
                int clip = (int) row.getData("CLIP");    
                
                //create the row
                OrderItem order = new OrderItem(orderId, itemId, itemAmount, photoId, startX, startY, endX, endY, brightness, sepia, noise, blur, saturation, hue, clip);
                result.add(order);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
