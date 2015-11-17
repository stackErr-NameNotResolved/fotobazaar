/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.domain;

import classes.database.DataTable;
import classes.database.DatabaseConnector;
import java.io.Serializable;
import classes.database.StatementResult;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.math.BigDecimal;
import javax.servlet.http.Part;

/**
 *
 * @author Bas
 */
public class Item implements Serializable {

    private int id;
    private double price;
    private String description;
    
    private DecimalFormat df;

    public Item() {
    }
    

    public static Item getItemFromId(int id) {
        Item i = new Item();
        DataTable dt = DatabaseConnector.getInstance().executeQuery("select description, price from item where id=?", id);
        if (dt.getRowCount() == 1) {
            i.id = id;
            i.description = (String) dt.getDataFromRow(0, "description");
            i.price = ((BigDecimal) dt.getDataFromRow(0, "price")).doubleValue();

            return i;
        }

        return null;
    }

    public double getPrice() {
        return price;
    }
    
    public String getPriceFormat()
    {
        return formatDouble(price);
    }

    public int getId() {
        return id;
    }
    

    @Override
    public String toString() {
        return this.description;
    }

    /**
     * Deletes an item in the Database
     *
     * @param itemId The id the item
     * @return if the item is deleted
     */
    public static boolean delete(int itemId) {

        boolean result = false;
        StatementResult dbResult = null;

        if (itemId > 0) {
            try {
                dbResult = DatabaseConnector.getInstance().executeNonQuery("DELETE FROM item WHERE id=?", itemId);
                result = true;
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            if (dbResult.equals(StatementResult.ERROR) || dbResult.equals(StatementResult.NO_ROWS_UPDATED)) {
                result = false;
            }
        }

        return result;
    }

    /**
     * Creates an item in the Database
     *
     * @param code The image code
     * @param description The description of the item
     * @param price The price of the item
     * @param isActive Is the item active
     * @param image The image of the item
     * @return if the item is created
     */
    public static boolean create(String code, String description, double price, boolean isActive, Part image) {
        boolean result = false;
        StatementResult dbResult = null;

        int dbIsActive = 0;
        if (isActive) {
            dbIsActive = 1;
        }

        if (price > 0.00) {
            if (!code.isEmpty() || !description.isEmpty()) {
                try {
                    if (image == null) {
                        //Upload without image
                        dbResult = DatabaseConnector.getInstance().executeNonQuery("INSERT INTO item (CODE, DESCRIPTION, PRICE, ACTIVE) VALUES (?,?,?,?)", description, code, price, dbIsActive);

                    } else {
                        //Upload with image
                        InputStream dbImage = image.getInputStream();
                        dbResult = DatabaseConnector.getInstance().executeNonQuery("INSERT INTO item (CODE, DESCRIPTION, PRICE, ACTIVE, IMAGE) VALUES (?,?,?,?,?)", description, code, price, dbIsActive, dbImage);
                    }

                    if (!dbResult.equals(StatementResult.ERROR) || !dbResult.equals(StatementResult.NO_ROWS_UPDATED)) {
                        result = true;
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }

        return result;
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
    /**
     * Changes the price of an item in the database
     * @param itemId The item ID
     * @param newPrice The new price of the item
     * @return If it has succes
     */
    public static boolean changePrice(int itemId, double newPrice){
        
        boolean result = false;
        
        if(itemId > 0 && newPrice >= 0.00){
            
            try{
                StatementResult dbResult = DatabaseConnector.getInstance().executeNonQuery("UPDATE item SET PRICE = ? WHERE ID = ?", newPrice, itemId);
                
                if(!dbResult.equals(StatementResult.ERROR) || !dbResult.equals(StatementResult.NO_ROWS_UPDATED)){
                    result = true;
                }                
            }catch (Exception ex){
                ex.printStackTrace();
            }            
        }       
        
        return result;        
    }
}
