/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.domain;

import classes.database.DataTable;
import classes.database.DatabaseConnector;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author sjorsvanmierlo
 */
public class ItemTest extends TestCase {
    
    @Before
    public void setUp() throws Exception {
    
        super.setUp();
        
    }
    @Test
    public void testCreateItem(){
        //Arrange
        final String description1 = "This item is a test! [1]";
        final String code1 = "0123456789";
        final double price1 = 99.99;
        final boolean isActive1 = true;
        final boolean expectedValue1 = true;
        
        
        final String description2 = "This item is a test! NOT ACTIVE! [2]";
        final String code2 = "0234567891";
        final double price2 = 10.00;
        final boolean isActive2 = false;
        final boolean expectedValue2 = true;
        
        final String description3 = "This item should not be in the database! [3]";
        final String code3 = "0345678912";
        final double price3 = -1;
        final boolean isActive3 = true;
        final boolean expectedValue3 = false;
                       
        //Act
        final boolean actualValue1 = Item.create(description1,code1, price1, isActive1, null);
        final boolean actualValue2 = Item.create(description2,code2, price2, isActive2, null);
        final boolean actualValue3 = Item.create(description3,code3, price3, isActive3, null);        
        
        //Assert
        Assert.assertEquals("This item1 should be inserted into the database.",expectedValue1, actualValue1);
        Assert.assertEquals("This item2 should be inserted into the database.",expectedValue2, actualValue2);
        Assert.assertEquals("This item3 should not be inserted into the database.",expectedValue3, actualValue3);
        
    }
    
    @Test
    public void testDeleteItem(){        
        //Arrange
        final String code1 = "0123456789";
        final String code2 = "0234567891";
        
        int id1 = 0;
        int id2 = 0;
        final int id3 = 999999;
        
        final boolean expectedValue1 = true;
        final boolean expectedValue2 = true;
        final boolean expectedValue3 = false;
        
        try{
        DataTable dt = DatabaseConnector.getInstance().executeQuery("SELECT * FROM item WHERE CODE = ? OR CODE = ? ",code1,code2);
        
        if(dt.containsData()){
            for(int i = 0; i< dt.getRowCount(); i++){
                  Object[] row = dt.getRow(i);
                switch(i){
                    case 0:                          
                            id1 = (int) row[0];
                        break;
                    case 1:
                            id2 = (int) row[0];
                        break;
                                    }
            }
        }
        
        }catch(Exception ex){
                ex.printStackTrace();
        }
                        
        //Act
        final boolean actualValue1 = Item.delete(id1);
        final boolean actualValue2 = Item.delete(id2);
        final boolean actualValue3 = Item.delete(id3);
        
        
        //Assert
        Assert.assertEquals("ID1 should be deleted",expectedValue1,actualValue1);
        Assert.assertEquals("ID2 should be deleted",expectedValue2,actualValue2);
        Assert.assertEquals("ID3 can't be deleted!",expectedValue3,actualValue3);
        
    }
    
    
}
