package com.jogo.map;

import java.awt.image.BufferedImage;
import com.jogo.constants.TileType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Tile {
    private final BufferedImage sprite;
    private final TileType type;

    public boolean isSolid() {
        return type.isSolid();
    }
}
