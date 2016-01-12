/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.domain;

import classes.database.DataTable;
import classes.database.DatabaseConnector;
import org.junit.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.fail;

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
            Object image = getClass().getResource("image1.png");
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
            DataTable dt = DatabaseConnector.getInstance().executeQuery("SELECT PRICE FROM PHOTO WHERE ID = ?", databaseId1);

            if (dt != null || dt.containsData()) {
                if (((BigDecimal) dt.getDataFromRow(0, "PRICE")).doubleValue() != price1) {
                    Assert.fail("Price1 did not update!");
                }
            }

            dt = null;
            dt = DatabaseConnector.getInstance().executeQuery("SELECT PRICE FROM PHOTO WHERE ID = ?", databaseId2);

            if (dt != null || dt.containsData()) {
                if (((BigDecimal) dt.getDataFromRow(0, "PRICE")).doubleValue() == price2) {
                    Assert.fail("Price2 did update!");
                }
            }

            dt = null;
            dt = DatabaseConnector.getInstance().executeQuery("SELECT PRICE FROM PHOTO WHERE ID = ?", databaseId3);

            if (dt != null || dt.containsData()) {
                if (((BigDecimal) dt.getDataFromRow(0, "PRICE")).doubleValue() != price3) {
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

    @Test
    public void testDeletePicture() {
        
        //See the testboard on VersionOne for test explanation.
        
        //ARRANGE
        final int expectedValue = 0;
        final boolean expectedResult = true;

        int photoId1 = 1;
        int photoId2 = 2;
        int photoId3 = 3;

        //ACT
        boolean actualResult1 = Picture.deletePicture(photoId1);
        boolean actualResult2 = Picture.deletePicture(photoId2);
        boolean actualResult3 = Picture.deletePicture(photoId3);

        //ASSERT
        Assert.assertTrue("Actual result should be true!", actualResult1);
        Assert.assertTrue("Actual result should be true!", actualResult2);
        Assert.assertTrue("Actual result should be true!", actualResult2);

        try {

            DataTable dt = DatabaseConnector.getInstance().executeQuery("SELECT ACTIVE FROM PHOTO WHERE ID = ?", photoId1);

            if (dt != null || dt.containsData()) {
                if ((int) dt.getDataFromRow(0, "ACTIVE") != expectedValue) {
                    Assert.fail("Active on photoId 1 did not update!");
                }
            }
            dt = DatabaseConnector.getInstance().executeQuery("SELECT ACTIVE FROM PHOTO WHERE ID = ?", photoId2);

            if (dt != null || dt.containsData()) {
                if ((int) dt.getDataFromRow(0, "ACTIVE") != expectedValue) {
                    Assert.fail("Active on photoId 2 did not update!");
                }
            }
            dt = DatabaseConnector.getInstance().executeQuery("SELECT ACTIVE FROM PHOTO WHERE ID = ?", photoId3);

            if (dt != null || dt.containsData()) {
                if ((int) dt.getDataFromRow(0, "ACTIVE") != expectedValue) {
                    Assert.fail("Active on photoId 3 did not update!");
                }
            }
        } catch (Exception ex) {
            fail("Could not connect to database!");
        }

    }
}
