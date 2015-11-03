/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bas
 */
public class Cart {

    private List<Order> orders;

    public Cart() {
        orders = new ArrayList();
    }

    public void addOrder(Item item, Picture picture, int aantal) {
        orders.add(new Order(picture, item, aantal));
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
    
    public Order[] getOverview()
    {
        Order[] array = new Order[orders.size()];
        return orders.toArray(array); 
    }
    
    public double getTotalPrice()
    {
        double total = 0;
        for (Order order : orders) {
            total += order.getTotalPrice();
        }
        
        return total;
    }
}
