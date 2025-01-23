package com.jogo.graphics;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

    private BufferedImage sheet;

    public SpriteSheet(String path) {
        try {
            sheet = ImageIO.read(new File(path));
        } catch (IOException e) {
            System.err.println("Erro ao carregar a imagem: " + path);
        }
    }

    public BufferedImage getSprite(int x, int y, int width, int height, int scale) {
        BufferedImage sprite = sheet.getSubimage(x * width, y * height, width, height);
        BufferedImage scaledSprite = new BufferedImage(width * scale, height * scale, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = scaledSprite.createGraphics();
        g2d.drawImage(sprite, 0, 0, width * scale, height * scale, null);
        g2d.dispose();
        return scaledSprite;
    }
}
