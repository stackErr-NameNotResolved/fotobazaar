/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.domain;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author sjorsvanmierlo
 */
public class Order implements Serializable {

    private int id;
    private int customer_id;
    private Timestamp orderDate;
    private boolean isPaid;

    /**
     * Empty constructor
     */
    public Order() {
    }

    /**
     * The constructor
     * @param id Order id
     * @param customer_id Customer id
     * @param orderDate Order id
     * @param isPaid If the order is paid?
     */
    public Order(int id, int customer_id, Timestamp orderDate, boolean isPaid) {
        this.id = id;
        this.customer_id = customer_id;
        this.orderDate = orderDate;
        this.isPaid = isPaid;
    }

    /**
     * Get the order id
     * @return the id
     */
    public int getId() {
        return id;
    }
    
    /**
     * Get the customer id
     * @return Customer id
     */
    public int getCustomer_id() {
        return customer_id;
    }
    /**
     * Get the order date
     * @return Timestamp of the date
     */
    public Timestamp getOrderDate() {
        return orderDate;
    }
    
    /**
     * Get the only the date
     * @return The date
     */
    public String getOrderDateString(){
        return orderDate.toString().substring(0,10);
    }

    /**
     * Did the user pay?
     * @return isPaid
     */
    public boolean isPaid() {
        return isPaid;
    }

}
