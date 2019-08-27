package me.vapor.botforgod.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Renderer {
    public String genImg(String text){
        BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2d = image.createGraphics();
        Font font = new Font("Futura", Font.BOLD, 30);
        graphics2d.setFont(font);
        FontMetrics fontmetrics = graphics2d.getFontMetrics();
        int width = fontmetrics.stringWidth(text);
        int height = fontmetrics.getHeight();
        graphics2d.dispose();
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        graphics2d = image.createGraphics();
        graphics2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        graphics2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        graphics2d.setFont(font);
        fontmetrics = graphics2d.getFontMetrics();
        graphics2d.setColor(Color.MAGENTA);
        graphics2d.drawString(text, 0, fontmetrics.getAscent());
        graphics2d.dispose();
        char[] chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder imgName = new StringBuilder();
        for(int i = 0; i < 15; i++){
            imgName.append(chars[new Random().nextInt(chars.length)]);
        }
        try {
            ImageIO.write(image, "png", new File(imgName.toString() + ".png"));
            return imgName.toString();
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
