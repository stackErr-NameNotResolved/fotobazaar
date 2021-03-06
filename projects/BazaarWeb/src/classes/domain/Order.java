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
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author sjorsvanmierlo
 */
public class Order implements Serializable {

    private int id;
    private int customer_id;
    private Timestamp orderDate;
    private boolean isPaid;
    private boolean isDone;

    /**
     * Empty constructor
     */
    public Order() {
    }

    /**
     * The constructor
     *
     * @param id Order id
     * @param customer_id Customer id
     * @param orderDate Order id
     * @param isPaid If the order is paid?
     * @param isDone
     */
    public Order(int id, int customer_id, Timestamp orderDate, boolean isPaid, boolean isDone) {
        this.id = id;
        this.customer_id = customer_id;
        this.orderDate = orderDate;
        this.isPaid = isPaid;
        this.isDone = isDone;
    }

    /**
     * Get the order id
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Get the customer id
     *
     * @return Customer id
     */
    public int getCustomer_id() {
        return customer_id;
    }

    /**
     * Get the order date
     *
     * @return Timestamp of the date
     */
    public Timestamp getOrderDate() {
        return orderDate;
    }

    /**
     * Get the only the date
     *
     * @return The date
     */
    public String getOrderDateString() {
        return orderDate.toString().substring(0, 10);
    }

    /**
     * Did the user pay?
     *
     * @return isPaid
     */
    public boolean isPaid() {
        return isPaid;
    }

    /**
     * Edit the DB that the order is paid
     *
     * @param orderId The id of the order
     */
    public static void isPaid(int orderId) {
        try {
            DatabaseConnector.getInstance().executeNonQuery("UPDATE order SET PAID=? WHERE ID=?", 1, orderId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Is the order done?
     *
     * @return isDone
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Set the order on doen
     *
     * @param orderId The orderId
     */
    public static void isDone(int orderId) {
        try {
            DatabaseConnector.getInstance().executeNonQuery("UPDATE `order` SET DONE=? WHERE ID=?", 1, orderId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
            DataTable dbResult = DatabaseConnector.getInstance().executeQuery("SELECT ID,CUSTOMER_ID,ORDERDATE,PAID,DONE FROM `Order` ORDER BY ORDERDATE DESC");

            while (true) {
                //Take the next row
                Object[] row = dbResult.getNextRow();
                
                if (row.length==0) {
                    break;
                }

                int id = (int) row[0];
                int customer_id = (int) row[1];
                Timestamp orderdate = (Timestamp) row[2];
                boolean isPaid = (int) row[3] == 1;
                boolean isDone = (int) row[4] == 1;

                //create the row
                Order order = new Order(id, customer_id, orderdate, isPaid, isDone);
                result.add(order);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;
    }
    
    public static Order get(int orderId){
        Order result = null;
        
        try{
            DataTable dbResult = DatabaseConnector.getInstance().executeQuery("SELECT * FROM `Order` WHERE ID = ?", orderId);
            
            while (true) {
                //Take the next row
                Object[] row = dbResult.getNextRow();
                
                if (row.length==0) {
                    break;
                }

                int id = (int) row[0];
                int customer_id = (int) row[1];
                Timestamp orderdate = (Timestamp) row[2];
                boolean isPaid = (int) row[3] == 1;
                boolean isDone = (int) row[4] == 1;

                //create the row
                Order order = new Order(id, customer_id, orderdate, isPaid, isDone);
                return order;
            }
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        return result;
    }

}
