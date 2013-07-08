package com.f0gg.imagenoid.utils;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

/**
 *
 * @author f0gg
 * @see http://github.com/f0gg
 */
public class ImageResize {

    private static final int IMG_WIDTH = 100;
    private static final int IMG_HEIGHT = 100;

    public int[] getSizeLdpi(BufferedImage originalImage) {
        int w = originalImage.getWidth();
        int h = originalImage.getHeight();
        return new int[]{(w / 8 * 3), (h / 8 * 3)};
    }

    public int[] getSizeMdpi(BufferedImage originalImage) {
        int w = originalImage.getWidth();
        int h = originalImage.getHeight();
        return new int[]{(w / 8 * 4), (h / 8 * 4)};
    }

    public int[] getSizeHdpi(BufferedImage originalImage) {
        int w = originalImage.getWidth();
        int h = originalImage.getHeight();
        return new int[]{(w / 8 * 6), (h / 8 * 6)};
    }

    public BufferedImage resizeImage(BufferedImage originalImage, int type, int[] sizesXY) {
        return resizeImage(originalImage, type, sizesXY[0], sizesXY[1]);
    }
    
    public BufferedImage resizeImage(BufferedImage originalImage, int type) {
        return resizeImage(originalImage, type, IMG_WIDTH, IMG_HEIGHT);
    }

    public BufferedImage resizeImage(BufferedImage originalImage, int type, int x, int y) {
        BufferedImage resizedImage = new BufferedImage(x, y, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, x, y, null);
        g.dispose();

        return resizedImage;
    }

    public BufferedImage resizeImageWithHint(BufferedImage originalImage, int type) {
        BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
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
