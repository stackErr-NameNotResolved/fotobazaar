/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.domain;

import classes.database.DataTable;
import classes.database.DatabaseConnector;
import java.math.BigDecimal;

/**
 *
 * @author Bas
 */
public class Item {
    private int id;
    private String pictureCode;
    private double price;
    private String description;
    
    public Item(int id)
    {
        this.id = id;
        DataTable retVal = DatabaseConnector.getInstance().executeQuery("SELECT CODE,DESCRIPTION,PRICE FROM item WHERE ID = ?", id);
        this.pictureCode = (String) retVal.getRow(0)[0];
        this.description = (String) retVal.getRow(0)[1];
        this.price = ((BigDecimal)retVal.getRow(0)[2]).doubleValue();
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
