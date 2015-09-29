/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.picture;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

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
        
        BufferedImage convertedImage1 = Picture.getThumbnail(imageToConvert1,maximumSize);
        BufferedImage convertedImage2 = Picture.getThumbnail(imageToConvert2,maximumSize);
        BufferedImage convertedImage3 = Picture.getThumbnail(imageToConvert3,maximumSize);

        if (convertedImage1.getWidth() != maximumSize || convertedImage2.getHeight() != maximumSize || convertedImage3.getHeight() != maximumSize||convertedImage3.getWidth()!= maximumSize) {
            fail("atleast the width or height needs to be "+maximumSize+" pixels or smaller when the image is smaller than "+maximumSize+" pixels");
        }
    }
}
