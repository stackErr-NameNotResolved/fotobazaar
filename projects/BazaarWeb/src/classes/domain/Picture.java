/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.domain;

import classes.database.DataTable;
import classes.database.DatabaseConnector;
import classes.database.StatementResult;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.servlet.http.Part;

/**
 *
 * @author Jip
 */
public class Picture {

    private BufferedImage photo;

    /**
     *
     */
    public Picture() {

    }

    /**
     * uploads the picture to the database and places a thumbnail in the
     * database
     *
     * @param imagePart part that containt the image
     * @param photographerId the id of the photographer
     * @param price price of the image
     * @param thumbnailSize the max width or height of the thumbnail
     * @return true if the image got placed in the database, false if nothing
     * got updated due part being null or on constraint violation
     */
    public static boolean uploadPicture(Part imagePart, int photographerId, double price, int thumbnailSize) {
        if (imagePart == null) {
            return false;
        }

        try {
            InputStream imageBig = imagePart.getInputStream();
            InputStream imageBigCopy = imagePart.getInputStream();//copy is made because filedescriptor is walked to the end due to the inputStreamToBufferedImage function
            InputStream imageSmall = BufferedImageToInputstream(getThumbnail(inputStreamToBufferedImage(imageBigCopy), thumbnailSize), imagePart.getSubmittedFileName());

            StatementResult result = DatabaseConnector.getInstance().executeNonQuery("INSERT INTO photo (CODE,PHOTOGRAPHER_ID,PRICE,DATA_BIG,DATA_SMALL,ACTIVE) VALUES (?,?,?,?,?,?)", generateNewID(), photographerId, price, imageBig, imageSmall,1);

            if (result.equals(StatementResult.ERROR) | result.equals(StatementResult.NO_ROWS_UPDATED)) {
                return false;
            }
            return true;
        } catch (IOException ex) {
            Logger.getLogger(Picture.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     * converts an inputstream to a bufferedimage
     *
     * @param input the inputstream to convert
     * @return the newly created bufferedimage
     */
    public static BufferedImage inputStreamToBufferedImage(InputStream input) {
        BufferedImage returnImage = null;
        try {
            returnImage = ImageIO.read(input);
        } catch (IOException ex) {
            Logger.getLogger(Picture.class.getName()).log(Level.SEVERE, null, ex);
        }

        return returnImage;
    }

    /**
     * Converts the bufferedimage to an inputstream
     *
     * @param input the bufferedimage to convert
     * @param imagename the name of the image to convert with the .png or .jpg
     * postfix
     * @return the converted image as inputstream
     */
    public static InputStream BufferedImageToInputstream(BufferedImage input, String imagename) {
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            String[] splittedImagename = imagename.split("\\.");
            ImageIO.write(input, splittedImagename[splittedImagename.length - 1], os);
            InputStream is = new ByteArrayInputStream(os.toByteArray());
            return is;
        } catch (IOException ex) {
            Logger.getLogger(Picture.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Creates a thumbnail of the given picture
     *
     * @param originalPicture the picture to shrink
     * @param maximumSize
     * @return the actual thumbnail
     */
    public static BufferedImage getThumbnail(BufferedImage originalPicture, int maximumSize) {
        int newWidth = 0;
        int newHeight = 0;

        if (originalPicture.getWidth() > originalPicture.getHeight()) {
            newWidth = maximumSize;
            newHeight = originalPicture.getHeight() / (originalPicture.getWidth() / maximumSize);
        } else if (originalPicture.getWidth() < originalPicture.getHeight()) {
            newWidth = originalPicture.getWidth() / (originalPicture.getHeight() / maximumSize);
            newHeight = maximumSize;
        } else {
            newWidth = maximumSize;
            newHeight = maximumSize;
        }

        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, originalPicture.getType());
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalPicture, 0, 0, newWidth, newHeight, null);
        g.dispose();
        g.setComposite(AlphaComposite.Src);

        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        return resizedImage;
    }

    /**
     * Generates a new ID for an image
     *
     * @return the newly generated ID
     */
    public static String generateNewID() {
        // Get the new ID from the database
        int nextId = 1;
        try {
            nextId = ((BigDecimal) DatabaseConnector.getInstance().executeQuery("SELECT MAX(ID) AS ID FROM PHOTO").getDataFromRow(0, "ID")).intValue() + 1;
        } catch (Exception ex) {
        }

        long ticks = Calendar.getInstance().getTime().getTime();

        SecureRandom rand = new SecureRandom();
        long rand_val = ticks % nextId * rand.nextInt();

        StringBuilder sb = new StringBuilder();
        sb.append(nextId).append(ticks).append(rand_val).append(ticks);
        String total = sb.toString();

        int index = 0;
        while (index < total.length() - 2) {
            String _2Chars = total.substring(index, index + 2);
            try {
                int value = Math.abs(Integer.parseInt(_2Chars));

                if (value < 26) {
                    // Replace the character
                    sb = new StringBuilder(total);
                    sb.delete(index, index + 2);
                    sb.insert(index, (char) (97 + value));
                    total = sb.toString();
                } else {
                    if (rand.nextInt() % 10 < 5) {
                        value %= 26;
                        sb = new StringBuilder(total);
                        sb.delete(index, index + 2);
                        sb.insert(index, (char) (97 + value));
                        total = sb.toString();
                    }
                }
            } catch (Exception ex) {
            }

            index++;
        }

        if (total.length() > 15) {
            total = total.substring(0, 15);
        } else if (total.length() < 15) {
            while (total.length() < 15) {
                total += (char) (65 + rand.nextInt(26));
            }
        }

        total = total.replaceAll("o", "0").toUpperCase();

        // Check if the final UID exists in the database
        DataTable dt = DatabaseConnector.getInstance().executeQuery("SELECT COUNT(CODE) AS COUNT FROM PHOTO WHERE CODE=\'" + total + "\'");
        if (dt != null && dt.containsData()) {
            if ((long) dt.getDataFromRow(0, "COUNT") != 0) {
                total = classes.domain.Picture.generateNewID();
                //System.out.println("code was already used");
            }
            //        else {
            //            try {
            //                DatabaseConnector.Instance.executeNonQuery("INSERT INTO `photo`(`ID`, `CODE`, `PHOTOGRAPHER_ID`, `PRICE`, `DATA_BIG`) VALUES (?,?,?,?,?)", nextId, total, 1, 1, "data");
            //            } catch (Exception ex) {
            //            }
            //        }
        }

        //System.out.println(total);
        return total;
    }

    /**
     * *
     * updates the existing price of the picture.
     *
     * @param newPrice the new price of the picture.
     * @param photoId the ID of the photo
     * @return boolean if the price is updated or not.
     */
    public static boolean updatePrice(double newPrice, int photoId) {

        boolean result = false;

        if (newPrice >= 0.00) {
            try {
                StatementResult dbResult = DatabaseConnector.getInstance().executeNonQuery(String.format("UPDATE PHOTO SET PRICE = %s WHERE ID = %s", newPrice, photoId));

                if (dbResult == null || dbResult == StatementResult.ERROR || dbResult == StatementResult.NO_ROWS_UPDATED) {
                    result = false;
                } else {
                    result = true;
                }

            } catch (Exception ex) {
                result = false;
            }
        } else {
            result = false;
        }

        return result;
    }

    /**
     * sets the photo inactive in the database.
     *
     * @param photoId The ID of the photo.
     * @return boolean if the photo is set inactive or not.
     */
    public static boolean deletePicture(int photoId) {
        boolean result;

        try {
            StatementResult dbResult = DatabaseConnector.getInstance().executeNonQuery(String.format("UPDATE fotobazaar.PHOTO SET ACTIVE = 0 WHERE ID = %s", photoId));
            if (dbResult == null || dbResult == StatementResult.ERROR || dbResult == StatementResult.NO_ROWS_UPDATED) {
                result = false;
            } else {
                result = true;
            }
        } catch (Exception ex) {
            result = false;
        }

        return result;
    }
}
