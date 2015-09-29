/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java.classes.picture;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 *
 * @author Jip
 */
public class Picture {

    private BufferedImage photo;

    public Picture(){

    }

    public static BufferedImage getThumbnail(BufferedImage picture) {

        BufferedImage resizedImage = new BufferedImage(100, 100, picture.getType());
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(picture, 0, 0, 100, 100, null);
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
