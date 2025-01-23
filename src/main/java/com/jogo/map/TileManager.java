package com.jogo.map;

import java.awt.Graphics;

import com.jogo.constants.TileType;
import com.jogo.graphics.SpriteSheet;

public class TileManager {

    private final Tile[] tiles;
    private final int[][] map;
    private final int tileSize;

    public TileManager(int mapWidth, int mapHeight) {
        tiles = new Tile[TileType.values().length];
        map = new int[mapHeight][mapWidth];
        tileSize = 16;

        loadTiles();
        createMap();
    }

    private void loadTiles() {
        SpriteSheet sheet = new SpriteSheet("src/main/resources/tiles/sprint_tiles.png");

        tiles[TileType.DIRT.getId()] = new Tile(sheet.getSprite(4, 8, 16, 16, 3), TileType.DIRT);
    }

    private void createMap() {
        for (int[] map1 : map) {
            for (int col = 0; col < map[0].length; col++) {
                map1[col] = TileType.DIRT.getId();
            }
        }
    }

    public void render(Graphics g) {
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[0].length; col++) {
                int tileId = map[row][col];
                g.drawImage(tiles[tileId].getSprite(),
                        col * tileSize,
                        row * tileSize,
                        null);
            }
        }
    }

    public boolean isSolid(int x, int y) {
        int col = x / tileSize;
        int row = y / tileSize;

        if (row < 0 || row >= map.length || col < 0 || col >= map[0].length) {
            return true;
        }

        int tileId = map[row][col];
        return tiles[tileId].isSolid();
    }
}
