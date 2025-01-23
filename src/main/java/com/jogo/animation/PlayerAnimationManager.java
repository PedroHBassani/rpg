package com.jogo.animation;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import com.jogo.constants.PlayerConstants;
import com.jogo.graphics.Animation;
import com.jogo.graphics.SpriteSheet;

public class PlayerAnimationManager {

    private final Map<String, Map<String, Animation>> animations;
    private Animation currentAnimation;

    public PlayerAnimationManager() {
        animations = new HashMap<>();
    }

    public void setSpriteSheet(String path, String animationType, int delay) {
        SpriteSheet spriteSheet = new SpriteSheet(path);
        loadAnimations(spriteSheet, animationType, delay);
    }

    private void loadAnimations(SpriteSheet spriteSheet, String animationType, int delay) {
        int scale = 4;
        Map<String, Animation> directionAnimations = new HashMap<>();
        directionAnimations.put(PlayerConstants.DOWN.getValue(), new Animation(loadFrames(spriteSheet, 0, scale), delay));
        directionAnimations.put(PlayerConstants.UP.getValue(), new Animation(loadFrames(spriteSheet, 1, scale), delay));
        directionAnimations.put(PlayerConstants.RIGHT.getValue(), new Animation(loadFrames(spriteSheet, 2, scale), delay));
        directionAnimations.put(PlayerConstants.LEFT.getValue(), new Animation(loadFrames(spriteSheet, 3, scale), delay));
        animations.put(animationType, directionAnimations);
    }

    private BufferedImage[] loadFrames(SpriteSheet spriteSheet, int row, int scale) {
        BufferedImage[] frames = new BufferedImage[4];
        for (int i = 0; i < 4; i++) {
            frames[i] = spriteSheet.getSprite(i, row, 32, 32, scale);
        }
        return frames;
    }

    public void setCurrentAnimation(String animationType, String direction) {
        currentAnimation = animations.get(animationType).get(direction);
    }

    public void updateCurrentAnimation() {
        currentAnimation.update();
    }

    public BufferedImage getCurrentFrame() {
        return currentAnimation.getCurrentFrame();
    }
}
