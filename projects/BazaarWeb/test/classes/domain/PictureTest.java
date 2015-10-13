/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.domain;

import classes.database.DataTable;
import classes.database.DatabaseConnector;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Part;
import javax.imageio.ImageIO;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Jip
 */
public class PictureTest {

    public PictureTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
    }

    public void testUploadPicture() {
        if (Picture.uploadPicture(null, 1, 1, 1) != false) {
            fail("should return false because part == null");
        }
    }

    /**
     * Test of getThumbnail method, of class Picture.
     */
    @Test
    public void testGetThumbnail() {
        BufferedImage imageToConvert1 = null;
        BufferedImage imageToConvert2 = null;
        BufferedImage imageToConvert3 = null;

        try {
            imageToConvert1 = ImageIO.read(getClass().getResource("image1.png"));
            imageToConvert2 = ImageIO.read(getClass().getResource("image2.jpg"));
            imageToConvert3 = ImageIO.read(getClass().getResource("image3.jpg"));
        } catch (IOException ex) {
            fail("generic error. could not load image");
            Logger.getLogger(PictureTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        int maximumSize = 200;

        BufferedImage convertedImage1 = Picture.getThumbnail(imageToConvert1, maximumSize);
        BufferedImage convertedImage2 = Picture.getThumbnail(imageToConvert2, maximumSize);
        BufferedImage convertedImage3 = Picture.getThumbnail(imageToConvert3, maximumSize);

        if (convertedImage1.getWidth() != maximumSize || convertedImage2.getHeight() != maximumSize || convertedImage3.getHeight() == imageToConvert3.getHeight() || convertedImage3.getWidth() == imageToConvert3.getWidth()) {
            fail("atleast the width or height needs to be " + maximumSize + " pixels or smaller when the image is smaller than " + maximumSize + " pixels");
        }
    }

    /**
     * Test of updatePrice method, of class Picture.
     */
    @Test
    public void testUpdatePrice() {
        //ARRANGE
        // The new prices that will be tried to update.
        final double price1 = 0.01;
        final double price2 = -0.01;
        final double price3 = 0.00;

        // he picture ids that are going to be tested.
        final int databaseId1 = 1;
        final int databaseId2 = 2;
        final int databaseId3 = 3;

        // Create new picture instances.
        // The expected output from the updatePrice method.
        final boolean expected1 = true;
        final boolean expected2 = false;
        final boolean expected3 = true;

        //ACT
        //Let the method do its job.
        boolean actual1 = Picture.updatePrice(price1, databaseId1);
        boolean actual2 = Picture.updatePrice(price2, databaseId2);
        boolean actual3 = Picture.updatePrice(price3, databaseId3);

        //ASSERT
        //Test if the actual output is equal to the expected value.
        Assert.assertEquals("P1 should be updated!", expected1, actual1);
        Assert.assertEquals("P2 should not be updated!", expected2, actual2);
        Assert.assertEquals("P3 should be updated!", expected3, actual3);

        //Tests if the price are actually updated in the database
        //ID's 1 till 3 should exist in the database!
        try {
            DataTable dt = DatabaseConnector.getInstance().executeQuery(String.format("SELECT PRICE FROM PHOTO WHERE CODE = %s", databaseId1));

            if (dt != null || dt.containsData()) {
                if ((double) dt.getDataFromRow(0, "PRICE") != price1) {
                    Assert.fail("Price1 did not update!");
                }
            }

            dt = null;
            dt = DatabaseConnector.getInstance().executeQuery(String.format("SELECT PRICE FROM PHOTO WHERE CODE = %s", databaseId2));

            if (dt != null || dt.containsData()) {
                if ((double) dt.getDataFromRow(0, "PRICE") == price2) {
                    Assert.fail("Price2 did update!");
                }
            }

            dt = null;
            dt = DatabaseConnector.getInstance().executeQuery(String.format("SELECT PRICE FROM PHOTO WHERE CODE = %s", databaseId3));

            if (dt != null || dt.containsData()) {
                if ((double) dt.getDataFromRow(0, "PRICE") != price3) {
                    Assert.fail("Price3 did not update!");
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Test
    public void testGenerateUniekID() {
        // INITIALIZE
        ArrayList<String> list = new ArrayList();

        // ASSERT
        // Test 100 unique codes
        for (int i = 0; i < 100; i++) {
            String id = Picture.generateNewID();

            // Check if the id is unique
            if (list.contains(id)) {
                fail("The generated id has been generated before");
            }

            list.add(id);

            // Check if the id has 15 characters
            if (id.length() != 15) {
                fail("The generated id has not exact 15 chracters");
            }

            // Check if the id contains a O
            if (id.contains("O")) {
                fail("The generated id contains a character \'O\'");
            }

            // Check if the id has only uppercase characters
            if (!id.equals(id.toUpperCase())) {
                fail("The generated id contains lowercase characters");
            }
        }
    }
}
