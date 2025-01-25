package com.jogo.animation;

import java.awt.image.BufferedImage;

import com.jogo.graphics.Animation;
import com.jogo.graphics.SpriteSheet;

public class CoinAnimationManager {

    private Animation animation;

    public void setSpriteSheet(String path, int delay) {
        SpriteSheet spriteSheet = new SpriteSheet(path);
        loadAnimation(spriteSheet, delay);
    }

    private void loadAnimation(SpriteSheet spriteSheet, int delay) {
        int scale = 1;
        animation = new Animation(loadFrames(spriteSheet, scale), delay);
    }

    private BufferedImage[] loadFrames(SpriteSheet spriteSheet, int scale) {
        BufferedImage[] frames = new BufferedImage[15];
        for (int i = 0; i < 15; i++) {
            frames[i] = spriteSheet.getSprite(i, 0, 16, 16, scale);
        }
        return frames;
    }

    public void updateAnimation() {
        animation.update();
    }

    public BufferedImage getCurrentFrame() {
        return animation.getCurrentFrame();
    }
}
