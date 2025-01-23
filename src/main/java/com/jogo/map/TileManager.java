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

        tiles[TileType.TOP_GRASS_1.getId()] = new Tile(sheet.getSprite(0, 0, 16, 16, 3), TileType.TOP_GRASS_1);
        tiles[TileType.TOP_GRASS_2.getId()] = new Tile(sheet.getSprite(1, 0, 16, 16, 3), TileType.TOP_GRASS_2);
        tiles[TileType.TOP_GRASS_3.getId()] = new Tile(sheet.getSprite(0, 1, 16, 16, 3), TileType.TOP_GRASS_3);
        tiles[TileType.TOP_GRASS_4.getId()] = new Tile(sheet.getSprite(1, 1, 16, 16, 3), TileType.TOP_GRASS_4);

        tiles[TileType.DIRT_ROAD_TOP_LEFT.getId()] = new Tile(sheet.getSprite(3, 7, 16, 16, 3), TileType.DIRT_ROAD_TOP_LEFT);
        tiles[TileType.DIRT_ROAD_TOP_CENTER.getId()] = new Tile(sheet.getSprite(4, 7, 16, 16, 3), TileType.DIRT_ROAD_TOP_CENTER);
        tiles[TileType.DIRT_ROAD_TOP_RIGHT.getId()] = new Tile(sheet.getSprite(5, 7, 16, 16, 3), TileType.DIRT_ROAD_TOP_RIGHT);

        tiles[TileType.DIRT_ROAD_CENTER_LEFT.getId()] = new Tile(sheet.getSprite(3, 8, 16, 16, 3), TileType.DIRT_ROAD_CENTER_LEFT);
        tiles[TileType.DIRT_ROAD_CENTER.getId()] = new Tile(sheet.getSprite(4, 8, 16, 16, 3), TileType.DIRT_ROAD_CENTER);
        tiles[TileType.DIRT_ROAD_CENTER_RIGHT.getId()] = new Tile(sheet.getSprite(5, 8, 16, 16, 3), TileType.DIRT_ROAD_CENTER_RIGHT);

        tiles[TileType.DIRT_ROAD_BOTTOM_LEFT.getId()] = new Tile(sheet.getSprite(3, 9, 16, 16, 3), TileType.DIRT_ROAD_BOTTOM_LEFT);
        tiles[TileType.DIRT_ROAD_BOTTOM_CENTER.getId()] = new Tile(sheet.getSprite(4, 9, 16, 16, 3), TileType.DIRT_ROAD_BOTTOM_CENTER);
        tiles[TileType.DIRT_ROAD_BOTTOM_RIGHT.getId()] = new Tile(sheet.getSprite(5, 9, 16, 16, 3), TileType.DIRT_ROAD_BOTTOM_RIGHT);
    }

    private void createMap() {
        for (int[] map1 : map) {
            for (int col = 0; col < map[0].length; col++) {
                map1[col] = TileType.DIRT_ROAD_CENTER.getId();
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
