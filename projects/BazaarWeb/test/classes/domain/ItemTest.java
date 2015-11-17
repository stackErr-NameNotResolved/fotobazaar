/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.domain;

import classes.database.DataRow;
import classes.database.DataTable;
import classes.database.DatabaseConnector;
import java.math.BigDecimal;
import javax.servlet.http.Part;
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
    public void testCreateItem() {
        //Arrange
        final String description1 = "This item is a test! [1]";

        final double price1 = 99.99;
        final boolean isActive1 = true;
        final boolean expectedValue1 = true;

        final String description2 = "This item is a test! NOT ACTIVE! [2]";

        final double price2 = 10.00;
        final boolean isActive2 = false;
        final boolean expectedValue2 = true;

        final String description3 = "This item should not be in the database! [3]";

        final double price3 = -1;
        final boolean isActive3 = true;
        final boolean expectedValue3 = false;

        //Act
        final boolean actualValue1 = Item.create(description1, price1, isActive1, null);
        final boolean actualValue2 = Item.create(description2, price2, isActive2, null);
        final boolean actualValue3 = Item.create(description3, price3, isActive3, null);

        //Assert
        Assert.assertEquals("This item1 should be inserted into the database.", expectedValue1, actualValue1);
        Assert.assertEquals("This item2 should be inserted into the database.", expectedValue2, actualValue2);
        Assert.assertEquals("This item3 should not be inserted into the database.", expectedValue3, actualValue3);

        getLatestIds(2);

    }

    @Test
    public void testChangePriceItem() {
        //GET THE IDS TOP
        int[] ids = getLatestIds(2);
        int id1 = ids[0];
        int id2 = ids[1];

        final boolean actualValue1 = Item.changePrice(id1, 12.99);
        final boolean expectedValue1 = true;

        final boolean actualValue2 = Item.changePrice(id2, -12.99);
        final boolean expectedValue2 = false;

        final boolean actualValue3 = Item.changePrice(id1, 99);
        final boolean expectedValue3 = true;

        Assert.assertEquals("Price should be changed of id1.", expectedValue1, actualValue1);
        Assert.assertEquals("Price should not be changed of id2.", expectedValue2, actualValue2);
        Assert.assertEquals("Price should be changed of id1.", expectedValue3, actualValue3);
    }

    @Test
    public void testChangeItem() {

        int[] ids = getLatestIds(2);

        int id1 = ids[0];
        int id2 = ids[1];

        final String newDescription1 = "Blauw, mok met handvat";
        final double newPrice1 = 99.99;
        final boolean newIsActive1 = true;
        final Part newImage1 = null;

        final String newDescription2 = "Grijs, mok zonder handvat";
        final double newPrice2 = 4.95;
        final boolean newIsActive2 = false;
        final Part newImage2 = null;

        final String newDescription3 = "";
        final double newPrice3 = -0.01;
        final boolean newIsActive3 = false;
        final Part newImage3 = null;

        final boolean actualValue1 = Item.changeItem(id1, newDescription1, newPrice1, newIsActive1, newImage1);
        final boolean actualValue2 = Item.changeItem(id2, newDescription2, newPrice2, newIsActive2, newImage2);
        final boolean actualValue3 = Item.changeItem(id1, newDescription3, newPrice3, newIsActive3, newImage3);

        final boolean expectedValue1 = true;
        final boolean expectedValue2 = true;
        final boolean expectedValue3 = false;

        //Assert
        Assert.assertEquals("Should be true!", expectedValue1, actualValue1);
        Assert.assertEquals("Should be true!", expectedValue2, actualValue2);
        Assert.assertEquals("Should be false!", expectedValue3, actualValue3);

        int actualId1 = -1;
        String actualDescription1 = "";
        double actualPrice1 = -1;
        boolean actualActive1 = false;

        int actualId2 = -1;
        String actualDescription2 = "";
        double actualPrice2 = -1;
        boolean actualActive2 = false;

        try {
            DataTable dbResult = DatabaseConnector.getInstance().executeQuery("SELECT * FROM item WHERE ID = ? OR ID = ? ORDER BY ID DESC", id1, id2);
            int count = 0;
            for (DataRow row : dbResult) {
                int isActive = -1;
                switch (count) {
                    case 0:
                        actualId1 = (int) row.getData("ID");
                        actualDescription1 = (String) row.getData("DESCRIPTION");
                        actualPrice1 = ((BigDecimal) row.getData("PRICE")).doubleValue();
                        isActive = (int) row.getData("ACTIVE");
                        if (isActive == 1) {
                            actualActive1 = true;
                        }
                        break;
                    case 1:
                        actualId2 = (int) row.getData("ID");
                        actualDescription2 = (String) row.getData("DESCRIPTION");
                        actualPrice2 = ((BigDecimal) row.getData("PRICE")).doubleValue();
                        isActive = (int) row.getData("ACTIVE");
                        if (isActive == 1) {
                            actualActive2 = true;
                        }
                        break;
                }
                count++;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            Assert.fail();
        }

        Assert.assertEquals("The Id should be the same!", id1, actualId1);
        Assert.assertEquals("The Description should be the same!", newDescription1, actualDescription1);
        Assert.assertEquals("The price should be the same!", newPrice1, actualPrice1);
        Assert.assertEquals("The isActive should be the same!", newIsActive1, actualActive1);

        Assert.assertEquals("The Id should be the same!", id2, actualId2);
        Assert.assertEquals("The Description should be the same!", newDescription2, actualDescription2);
        Assert.assertEquals("The price should be the same!", newPrice2, actualPrice2);
        Assert.assertEquals("The isActive should be the same!", newIsActive2, actualActive2);

    }

    @Test
    public void testDeleteItem() {
        //Arrange
        int[] ids = getLatestIds(2);
        int id1 = ids[0];
        int id2 = ids[1];
        final int id3 = 999999;

        final boolean expectedValue1 = true;
        final boolean expectedValue2 = true;
        final boolean expectedValue3 = false;

        //Act
        final boolean actualValue1 = Item.delete(id1);
        final boolean actualValue2 = Item.delete(id2);
        final boolean actualValue3 = Item.delete(id3);

        //Assert
        Assert.assertEquals("ID1 should be deleted", expectedValue1, actualValue1);
        Assert.assertEquals("ID2 should be deleted", expectedValue2, actualValue2);
        Assert.assertEquals("ID3 can't be deleted!", expectedValue3, actualValue3);

    }

    private int[] getLatestIds(int limit) {

        int[] ids = new int[limit];

        try {
            DataTable dbResult = DatabaseConnector.getInstance().executeQuery("SELECT ID FROM item order by ID DESC LIMIT ?", limit);
            int count = 0;
            for (DataRow row : dbResult) {
                ids[count] = (int) row.getData("ID");
                count++;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ids;

    }

}
