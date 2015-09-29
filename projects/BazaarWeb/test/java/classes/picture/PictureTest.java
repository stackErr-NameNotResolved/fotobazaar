/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java.classes.picture;

import org.junit.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
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
        BufferedImage imageToConvert = null;
        
        try {
            imageToConvert = ImageIO.read(new File("image1.png"));
            fail("generic error. could not load image");
        } catch (IOException ex) {
            Logger.getLogger(PictureTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        BufferedImage convertedImage = Picture.getThumbnail(imageToConvert);
        
        if (convertedImage.getWidth()!=200&&convertedImage.getHeight()!=200) {
            fail("atleast the width or height needs to be 200 pixels");
        }
    }
}
