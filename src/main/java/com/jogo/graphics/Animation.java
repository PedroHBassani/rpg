package com.jogo.graphics;

import java.awt.image.BufferedImage;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Animation {

    private final BufferedImage[] frames;
    private final int delay;
    private int currentFrame = 0;
    private int time = 0;

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
