package com.jogo.graphics;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.jogo.entities.Player;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UIRenderer {

    private final Player player;
    private final Image barImage;
    private final Image healthImage;
    private final Image coinImage;
    private static final int SCALA = 2;

    public UIRenderer(Player player) throws IOException {
        this.player = player;
        this.barImage = ImageIO.read(new File("src/main/resources/menu/bar.png"));
        this.healthImage = ImageIO.read(new File("src/main/resources/menu/health_bar.png"));
        this.coinImage = ImageIO.read(new File("src/main/resources/menu/coin.png"));
    }

    public void render(Graphics g, int screenWidth, int screenHeight) {
        renderCoinAnimation(g, screenWidth);
        renderHealthBar(g, screenWidth);
    }

    private void renderHealthBar(Graphics g, int screenWidth) {
        int barImageWidth = barImage.getWidth(null) * SCALA;
        int barImageHeight = barImage.getHeight(null) * SCALA;
        int x = screenWidth - barImageWidth - 10;
        int y = 10 + coinImage.getHeight(null) * SCALA;

        g.drawImage(barImage, x, y, barImageWidth, barImageHeight, null);

        int healthWidth = (int) (29 * SCALA * (player.getHealth() / (float) player.getMaxHealth()));
        int difference = (int) (29 * SCALA - healthWidth);

        g.drawImage(healthImage, x + 5 + difference, y + 6, healthWidth, barImageHeight - 10, null);
    }

    private void renderCoinAnimation(Graphics g, int screenWidth) {
        int imageWidth = coinImage.getWidth(null) * SCALA;
        int imageHeight = coinImage.getHeight(null) * SCALA;
        int x = screenWidth - imageWidth - 10;
        int y = 10;

        g.drawImage(coinImage, x, y, imageWidth, imageHeight, null);
    }

}
