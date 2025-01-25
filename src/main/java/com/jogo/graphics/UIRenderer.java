package com.jogo.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.jogo.animation.CoinAnimationManager;
import com.jogo.entities.Player;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UIRenderer {

    private final Player player;
    private final Image healthImage;
    private static final int SCALA = 2;
    private final CoinAnimationManager coinAnimationManager;

    public UIRenderer(Player player) throws IOException {
        this.player = player;
        this.healthImage = ImageIO.read(new File("src/main/resources/menu/health.png"));
        this.coinAnimationManager = new CoinAnimationManager();
        this.coinAnimationManager.setSpriteSheet("src/main/resources/menu/coin.png", 2);
    }

    public void render(Graphics g, int screenWidth, int screenHeight) {
        renderHealthBar(g, screenWidth, screenHeight);
        renderCoinAnimation(g);
    }

    private void renderHealthBar(Graphics g, int screenWidth, int screenHeight) {
        int barWidth = 4 * SCALA;
        int barHeight = 51 * SCALA;
        int filledHeight = (int) ((player.getHealth() / (float) player.getMaxHealth()) * barHeight);

        int imageWidth = healthImage.getWidth(null) * SCALA;
        int imageHeight = healthImage.getHeight(null) * SCALA;
        int x = screenWidth - imageWidth - 10;
        int y = screenHeight - imageHeight - 10;

        g.drawImage(healthImage, x, y, imageWidth, imageHeight, null);

        int barX = x + 6 * SCALA;
        int barY = y + 7 * SCALA + (barHeight - filledHeight);
        g.setColor(Color.GREEN);
        g.fillRect(barX, barY, barWidth, filledHeight);
    }

    private void renderCoinAnimation(Graphics g) {
        coinAnimationManager.updateAnimation();
        BufferedImage currentCoinFrame = coinAnimationManager.getCurrentFrame();
        int coinWidth = currentCoinFrame.getWidth() * SCALA;
        int coinHeight = currentCoinFrame.getHeight() * SCALA;
        int x = 10;
        int y = 10;

        g.drawImage(currentCoinFrame, x, y, coinWidth, coinHeight, null);
    }

}
