/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.domain;

import java.io.Serializable;

/**
 *
 * @author Bas
 */
public class Order implements Serializable {
    private int id;
    private Picture picture;
    private Item item;
    private int amount;
   
    public Order(int id, Picture picture, Item item, int amount)
    {
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
    
    public double getTotalPrice()
    {
        return (this.picture.getPrice() + this.item.getPrice()) * this.amount;
    }
}
