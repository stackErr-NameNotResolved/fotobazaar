/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.picture;

import java.awt.*;
import java.awt.image.BufferedImage;
import classes.database.DatabaseConnector;
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.Calendar;

/**
 *
 * @author Jip
 */
public class Picture {

    private BufferedImage photo;

    public Picture() {

    }

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
    
    public static String generateNewID() {
        // Get the new ID from the database
        int nextId = 1;
        try {
            nextId = ((BigDecimal) DatabaseConnector.Instance.executeQuery("SELECT MAX(ID) AS ID FROM PHOTO").getDataFromRow(0, "ID")).intValue() + 1;
        } catch (Exception ex) {
        }

        long ticks = Calendar.getInstance().getTime().getTime();

        SecureRandom rand = new SecureRandom();
        long rand_val = ticks % nextId * rand.nextInt();

        StringBuilder sb = new StringBuilder();
        sb.append(ticks).append(nextId).append(rand_val).append(ticks);
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

        total = total.replaceAll("o", "0").toUpperCase().substring(0, 15);

        // Check if the final UID exists in the database
        if ((long)DatabaseConnector.Instance.executeQuery("SELECT COUNT(CODE) AS COUNT FROM PHOTO WHERE CODE=\'" + total + "\'").getDataFromRow(0, "COUNT") != 0) {
            total = Picture.generateNewID();
        } else {
            try {
                DatabaseConnector.Instance.executeNonQuery("INSERT INTO `photo`(`CODE`, `PHOTOGRAPHER_ID`, `PRICE`, `DATA_BIG`) VALUES (?,?,?,?)", total, 1, 1, "data");
            } catch (Exception ex) {
            }
        }

        System.out.println(total);
        return total;
    }

    public boolean updatePrice(double price2) {
        return false;
    }
}
