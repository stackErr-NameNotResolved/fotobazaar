/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.domain;


import org.junit.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
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

        if (convertedImage1.getWidth() != maximumSize || convertedImage2.getHeight() != maximumSize || convertedImage3.getHeight() != imageToConvert3.getHeight() || convertedImage3.getWidth() != imageToConvert3.getWidth()) {
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
        //Picture p1 = new Picture(databaseId1);
        //Picture p2 = new Picture(databaseId2);
        //Picture p3 = new Picture(databaseId3);

        // The expected output from the updatePrice method.
        final boolean expected1 = true;
        final boolean expected2 = false;
        final boolean expected3 = true;

        //ACT
        //Let the method do its job.
        //boolean actual1 = p1.updatePrice(price1);
        //boolean actual2 = p2.updatePrice(price2);
        //boolean actual3 = p3.updatePrice(price3);

        //ASSERT
        //Test if the actual output is equal to the expected value.
        //Assert.assertEquals("P1 should be updated!", expected1, actual1);
        //Assert.assertEquals("P2 should not be updated!", expected2, actual2);
        //Assert.assertEquals("P3 should be updated!", expected3, actual3);

    }
}
