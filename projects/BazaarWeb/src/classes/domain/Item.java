/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.domain;

import classes.database.DataTable;
import classes.database.DatabaseConnector;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author Bas
 */
public class Item implements Serializable {
    private int id;
    private double price;
    private String description;
    
    public Item()
    {
        this.description = "Witte mok";
        this.price = 2.5;
        this.id =1;
    }
    
    public static Item getItemFromId(int id)
    {
        Item i = new Item();
        DataTable dt = DatabaseConnector.getInstance().executeQuery("select description, price from item where id=?", id);
        if(dt.getRowCount() == 1)
        {
            i.id = id;
            i.description = (String)dt.getDataFromRow(0, "description");
            i.price = ((BigDecimal)dt.getDataFromRow(0, "price")).doubleValue();
            
            return i;
        }
        
        return null;
    }

    public double getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }
    
    @Override
    public String toString()
    {
        return this.description;
    }
}
