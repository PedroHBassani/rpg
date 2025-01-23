package com.jogo.graphics;

import com.jogo.entities.Player;

public class Camera {
    private int x, y;

    public Camera() {
        this.x = 0;
        this.y = 0;
    }

    public void update(Player player, int screenWidth, int screenHeight) {
        this.x = player.getX() - screenWidth / 2;
        this.y = player.getY() - screenHeight / 2;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
