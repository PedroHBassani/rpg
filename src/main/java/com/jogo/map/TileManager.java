package com.jogo.map;

import java.awt.Graphics;

import com.jogo.constants.TileType;
import com.jogo.graphics.SpriteSheet;

public class TileManager {

    private final Tile[] tiles;
    private final int[][] map;
    private final int tileSize;
    private final SpriteSheet sheet;

    public TileManager(int mapWidth, int mapHeight) {
        tiles = new Tile[TileType.values().length];
        map = new int[mapHeight][mapWidth];
        tileSize = 16;
        sheet = new SpriteSheet("src/main/resources/tiles/sprint_tiles.png");

        loadTiles();
        createMap();
    }

    private void loadTiles() {
        loadTile(TileType.TOP_GRASS_1, 0, 0);
        loadTile(TileType.TOP_GRASS_2, 1, 0);
        loadTile(TileType.TOP_GRASS_3, 0, 1);
        loadTile(TileType.TOP_GRASS_4, 1, 1);

        loadTile(TileType.DIRT_TOP_LEFT, 3, 7);
        loadTile(TileType.DIRT_TOP_CENTER, 4, 7);
        loadTile(TileType.DIRT_TOP_RIGHT, 5, 7);

        loadTile(TileType.DIRT_CENTER_LEFT, 3, 8);
        loadTile(TileType.DIRT_CENTER, 4, 8);
        loadTile(TileType.DIRT_CENTER_RIGHT, 5, 8);

        loadTile(TileType.DIRT_BOTTOM_LEFT, 3, 9);
        loadTile(TileType.DIRT_BOTTOM_CENTER, 4, 9);
        loadTile(TileType.DIRT_BOTTOM_RIGHT, 5, 9);

        loadTile(TileType.HILL_TOP_LEFT, 5, 0);
        loadTile(TileType.HILL_TOP_RIGHT, 6, 0);
        loadTile(TileType.HILL_BOTTOM_LEFT, 5, 1);
        loadTile(TileType.HILL_BOTTOM_RIGHT, 6, 1);

        loadTile(TileType.RAVINE_TOP_LEFT, 7, 0);
        loadTile(TileType.RAVINE_TOP_CENTER, 8, 0);
        loadTile(TileType.RAVINE_TOP_RIGHT, 9, 0);
        loadTile(TileType.RAVINE_CENTER_LEFT, 7, 1);
        loadTile(TileType.RAVINE_CENTER_RIGHT, 9, 1);
        loadTile(TileType.RAVINE_BOTTOM_LEFT, 7, 2);
        loadTile(TileType.RAVINE_BOTTOM_CENTER, 8, 2);
        loadTile(TileType.RAVINE_BOTTOM_RIGHT, 9, 2);

        loadTile(TileType.DIRT_WALL, 8, 3);
        loadTile(TileType.SAND, 9, 3);

        loadTile(TileType.RED_MUSHROOM, 5, 3);
        loadTile(TileType.WHITE_FLOWER, 5, 4);
        loadTile(TileType.YELLOW_FLOWER, 6, 3);
        loadTile(TileType.GRASS, 6, 4);

        loadTile(TileType.WATER, 1, 9);

        loadTile(TileType.WATER_RAVINE_TOP_LEFT, 0, 8);
        loadTile(TileType.WATER_RAVINE_TOP_CENTER, 1, 8);
        loadTile(TileType.WATER_RAVINE_TOP_RIGHT, 2, 8);
        loadTile(TileType.WATER_RAVINE_CENTER_LEFT, 0, 9);
        loadTile(TileType.WATER_RAVINE_CENTER_RIGHT, 2, 9);
        loadTile(TileType.WATER_RAVINE_BOTTOM_LEFT, 0, 10);
        loadTile(TileType.WATER_RAVINE_BOTTOM_CENTER, 1, 10);
        loadTile(TileType.WATER_RAVINE_BOTTOM_RIGHT, 2, 10);

        loadTile(TileType.DIRT_ROAD_TOP_LEFT, 3, 10);
        loadTile(TileType.DIRT_ROAD_TOP_CENTER, 4, 10);
        loadTile(TileType.DIRT_ROAD_TOP_RIGHT, 5, 10);
        loadTile(TileType.DIRT_ROAD_CENTER_LEFT, 3, 11);
        loadTile(TileType.DIRT_ROAD_CENTER, 4, 11);
        loadTile(TileType.DIRT_ROAD_CENTER_RIGHT, 5, 11);
        loadTile(TileType.DIRT_ROAD_BOTTOM_LEFT, 3, 12);
        loadTile(TileType.DIRT_ROAD_BOTTOM_CENTER, 4, 12);
        loadTile(TileType.DIRT_ROAD_BOTTOM_RIGHT, 5, 12);
    }

    private void loadTile(TileType tileType, int x, int y) {
        tiles[tileType.getId()] = new Tile(sheet.getSprite(x, y, 16, 16, 3), tileType);
    }

    private void createMap() {
        for (int[] map1 : map) {
            for (int col = 0; col < map[0].length; col++) {
                map1[col] = TileType.DIRT_CENTER.getId();
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
