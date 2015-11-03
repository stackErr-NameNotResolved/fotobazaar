/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.domain;

/**
 *
 * @author Bas
 */
public class Item {
    private int id;
    private double price;
    private String description;
    
    public Item()
    {
        this.description = "Witte mok";
        this.price = 2.5;
    }
    
    public Item getItemFromId()
    {
        return null;
    }

    public double getPrice() {
        return price;
    }
    
    @Override
    public String toString()
    {
        return this.description;
    }
}
