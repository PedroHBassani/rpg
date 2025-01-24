package com.jogo.interfaces;

import java.awt.image.BufferedImage;

import com.jogo.constants.ItemType;
import com.jogo.graphics.SpriteSheet;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Item {

    protected static SpriteSheet sheet = new SpriteSheet("src/main/resources/tiles/sprint_tiles.png");

    private final String name;
    private final BufferedImage sprite;
    private final ItemType type;
}
