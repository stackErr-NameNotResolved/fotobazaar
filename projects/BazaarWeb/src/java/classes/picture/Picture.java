/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.picture;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

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
}
