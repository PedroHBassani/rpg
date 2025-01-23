package com.jogo.graphics;

import java.awt.image.BufferedImage;

public class Animation {

    private final BufferedImage[] frames;
    private final int delay;
    private int currentFrame;
    private int time;

    public Animation(BufferedImage[] frames, int delay) {
        this.frames = frames;
        this.delay = delay;
        currentFrame = 0;
        time = 0;
    }

    public void update() {
        time++;
        if (time > delay) {
            currentFrame++;
            time = 0;
            if (currentFrame >= frames.length) {
                currentFrame = 0;
            }
        }
    }

    public BufferedImage getCurrentFrame() {
        return frames[currentFrame];
    }
}
