package com.jogo.map;

import java.awt.image.BufferedImage;

import com.jogo.constants.TileType;

public class Tile {

    private final BufferedImage sprite;
    private final TileType type;

    public Tile(BufferedImage sprite, TileType type) {
        this.sprite = sprite;
        this.type = type;
    }

    public BufferedImage getSprite() {
        return sprite;
    }

    public TileType getType() {
        return type;
    }

    public boolean isSolid() {
        return type.isSolid();
    }
}
