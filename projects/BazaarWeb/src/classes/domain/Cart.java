/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.domain;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 *
 * @author Bas
 */
public class Cart implements Serializable {

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

    public static Cart fromString(String s) throws IOException,
            ClassNotFoundException {
        byte[] data = Base64.getDecoder().decode(s);
        ObjectInputStream ois = new ObjectInputStream(
                new ByteArrayInputStream(data));
        Cart o = (Cart)ois.readObject();
        ois.close();
        return o;
    }

    public static String toString(Cart o) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(o);
        oos.close();
        return Base64.getEncoder().encodeToString(baos.toByteArray());
    }
}
