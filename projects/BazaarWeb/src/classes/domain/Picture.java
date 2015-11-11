/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.domain;

import classes.database.DataTable;
import classes.database.DatabaseConnector;
import classes.database.StatementResult;

import javax.imageio.ImageIO;
import javax.servlet.http.Part;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Jip
 */
public class Picture implements Serializable {

    private double price;

    private int id;
    
    private float startX;
    private float startY;
    private float endX;
    private float endY;
    private int brightness;
    private int sepia;
    private int noise;
    private int blur;
    private int saturation;
    private int hue;
    private int clip;
    
    private DecimalFormat df;

    /**
     *
     */
    public Picture() {
        this.price = 1.0;
    }
    
    public Picture(int databaseId, float startX, float startY, float endX, float endY, int brightness, int sepia, int noise, int blur, int saturation, int hue, int clip)
    {
        this.id = databaseId;
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.brightness = brightness;
        this.sepia = sepia;
        this.noise = noise;
        this.saturation = saturation;
        this.hue = hue;
        this.clip = clip;
        
        DataTable dt = DatabaseConnector.getInstance().executeQuery("select price from photo where id=?", databaseId);
        if(dt.getRowCount() > 0)
        {
            price = ((BigDecimal)dt.getDataFromRow(0, "price")).doubleValue();
        }
    }

    public float getStartX() {
        return startX;
    }

    public float getStartY() {
        return startY;
    }

    public float getEndX() {
        return endX;
    }

    public float getEndY() {
        return endY;
    }

    public int getBrightness() {
        return brightness;
    }

    public int getSepia() {
        return sepia;
    }

    public int getNoise() {
        return noise;
    }

    public int getBlur() {
        return blur;
    }

    public int getSaturation() {
        return saturation;
    }

    public int getHue() {
        return hue;
    }

    public int getClip() {
        return clip;
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

    public void setPrice(double price) {
        this.price = price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStartX(float startX) {
        this.startX = startX;
    }

    public void setStartY(float startY) {
        this.startY = startY;
    }

    public void setEndX(float endX) {
        this.endX = endX;
    }

    public void setEndY(float endY) {
        this.endY = endY;
    }

    public void setBrightness(int brightness) {
        this.brightness = brightness;
    }

    public void setSepia(int sepia) {
        this.sepia = sepia;
    }

    public void setNoise(int noise) {
        this.noise = noise;
    }

    public void setBlur(int blur) {
        this.blur = blur;
    }

    public void setSaturation(int saturation) {
        this.saturation = saturation;
    }

    public void setHue(int hue) {
        this.hue = hue;
    }

    public void setClip(int clip) {
        this.clip = clip;
    }
    
    public void addEffects(int startX, int startY, int endX, int endY, int brightness, int sepia, int noise, int blur, int saturation, int hue, int clip) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.brightness = brightness;
        this.sepia = sepia;
        this.noise = noise;
        this.blur = blur;
        this.saturation = saturation;
        this.hue = hue;
        this.clip = clip;
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

            StatementResult result = DatabaseConnector.getInstance().executeNonQuery("INSERT INTO photo (CODE,PHOTOGRAPHER_ID,PRICE,DATA_BIG,DATA_SMALL,ACTIVE) VALUES (?,?,?,?,?,?)", generateNewID(), photographerId, price, imageBig, imageSmall, 1);

            if (result.equals(StatementResult.ERROR) | result.equals(StatementResult.NO_ROWS_UPDATED)) {
                return false;
            }
            return true;
        } catch (IOException ex) {
            Logger.getLogger(Picture.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static byte[] downloadImage(String imageCode, String iType) {
        DataTable result = null;
        byte[] blobbytes = null;
        String imageType = "";

        //map input to query
        if (iType.equals("big")) {
            imageType = "DATA_BIG";
            if (false) {//TODO: if the user is not admin
                imageType = "DATA_SMALL";
            }
        } else {
            imageType = "DATA_SMALL";
        }

        if (isPicturePublished(imageCode)) {
            result = DatabaseConnector.getInstance().executeQuery("SELECT " + imageType + " AS IMAGE FROM photo WHERE CODE = ?", imageCode);
            if (result == null) {
                result = DatabaseConnector.getInstance().executeQuery("SELECT IMAGE FROM item WHERE CODE = ?", imageCode);
                if (result != null) {
                    try {
                        result.getResultSet().next(); // Get first resultset result.
                        blobbytes = result.getResultSet().getBytes("IMAGE");
                    } catch (SQLException ex) {
                        throw new IllegalStateException("Cannot download image.", ex);
                    }
                    return blobbytes;
                }
            }
        }
        return null;
    }

    /**
     * returns true if a picture exists and is made public else false
     *
     * @param pictureCode code if the picture
     * @return returns true if a picture exists and is made public else false
     */
    public static boolean isPicturePublished(String pictureCode) {
        if (pictureCode != null) {
            //check if image actually exists in photo table
            if ((long) DatabaseConnector.getInstance().executeQuery("SELECT count(ID) FROM photo WHERE CODE = ?", pictureCode).getRow(0)[0] > 0) {
                //check if image is published
                if ((int) DatabaseConnector.getInstance().executeQuery("SELECT ACTIVE FROM photo WHERE CODE = ?", pictureCode).getRow(0)[0] == 1) {
                    return true;
                }
            }

            //check if image actually exists in items table
            if ((long) DatabaseConnector.getInstance().executeQuery("SELECT count(ID) FROM item WHERE CODE = ?", pictureCode).getRow(0)[0] > 0) {
                //check if image is published
                if ((int) DatabaseConnector.getInstance().executeQuery("SELECT ACTIVE FROM item WHERE CODE = ?", pictureCode).getRow(0)[0] == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * returns true if a picture exists else false
     *
     * @param pictureId id of the picture, can be null
     * @param pictureCode code if the picture, can be null
     * @return returns true if a picture exists else false
     */
    public static boolean isPictureAvailable(String pictureId, String pictureCode) {
        if (pictureId != null) {
            //check if image actually exists
            if ((long) DatabaseConnector.getInstance().executeQuery("SELECT count(ID) FROM photo WHERE ID = ?", pictureId).getRow(0)[0] > 0) {
                return true;
            }
        } else if (pictureCode != null) {
            //check if image actually exists
            if ((long) DatabaseConnector.getInstance().executeQuery("SELECT count(ID) FROM photo WHERE CODE = ?", pictureCode).getRow(0)[0] > 0) {
                return true;
            }
        }
        return false;
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

    public Picture getPictureFromId(int id) {
        return null;
    }
    
    public String getCookieData()
    {
        StringBuilder sb = new StringBuilder();
        
        String splitChar = "/";
        
        sb.append("id=").append(this.id).append(splitChar);
        
        sb.append("sx=").append(this.startX).append(splitChar);
        sb.append("sy=").append(this.startY).append(splitChar);
        sb.append("ex=").append(this.endX).append(splitChar);
        sb.append("ey=").append(this.endY).append(splitChar);
        
        sb.append("b=").append(this.brightness).append(splitChar);
        sb.append("s=").append(this.sepia).append(splitChar);
        sb.append("n=").append(this.noise).append(splitChar);
        sb.append("l=").append(this.blur).append(splitChar);
        sb.append("t=").append(this.saturation).append(splitChar);
        sb.append("h=").append(this.hue).append(splitChar);
        sb.append("c=").append(this.clip).append(splitChar);

        return sb.toString();
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
}
