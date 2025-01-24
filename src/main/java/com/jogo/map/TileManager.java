package com.jogo.map;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.jogo.constants.TileType;
import com.jogo.graphics.SpriteSheet;

public class TileManager {

    private final Tile[] tiles;
    private int[][] map;
    private final int tileSize;
    private final SpriteSheet sheet;

    public TileManager(String mapImagePath) {
        tileSize = 16 * 3;
        sheet = new SpriteSheet("src/main/resources/tiles/sprint_tiles.png");
        tiles = new Tile[TileType.values().length];

        loadTiles();
        loadMapFromImage(mapImagePath);
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

    private void createMap(int width, int height) {
        map = new int[height][width];
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

    public void loadMapFromImage(String imagePath) {
        try {
            BufferedImage image = ImageIO.read(new File(imagePath));
            int width = image.getWidth();
            int height = image.getHeight();
            createMap(width, height);
            for (int row = 0; row < height && row < map.length; row++) {
                for (int col = 0; col < width && col < map[0].length; col++) {
                    int pixel = image.getRGB(col, row);
                    TileType tileType = getTileTypeFromColor(pixel);
                    if (tileType != null) {
                        map[row][col] = tileType.getId();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private TileType getTileTypeFromColor(int color) {
        switch (color) {
            case 0x1A2B3C:  // TOP_GRASS_1
                return TileType.TOP_GRASS_1;
            case 0x2B3C4D:  // TOP_GRASS_2
                return TileType.TOP_GRASS_2;
            case 0x3C4D5E:  // TOP_GRASS_3
                return TileType.TOP_GRASS_3;
            case 0x4D5E6F:  // TOP_GRASS_4
                return TileType.TOP_GRASS_4;
        
            case 0x5E6F70:  // DIRT_TOP_RIGHT
                return TileType.DIRT_TOP_RIGHT;
            case 0x6F7071:  // DIRT_TOP_CENTER
                return TileType.DIRT_TOP_CENTER;
            case 0x707172:  // DIRT_TOP_LEFT
                return TileType.DIRT_TOP_LEFT;
            case 0x818283:  // DIRT_CENTER_RIGHT
                return TileType.DIRT_CENTER_RIGHT;
            case 0x929394:  // DIRT_CENTER
                return TileType.DIRT_CENTER;
            case 0xA1B2C3:  // DIRT_CENTER_LEFT
                return TileType.DIRT_CENTER_LEFT;
            case 0xB1C2D3:  // DIRT_BOTTOM_RIGHT
                return TileType.DIRT_BOTTOM_RIGHT;
            case 0xC1D2E3:  // DIRT_BOTTOM_CENTER
                return TileType.DIRT_BOTTOM_CENTER;
            case 0xD1E2F3:  // DIRT_BOTTOM_LEFT
                return TileType.DIRT_BOTTOM_LEFT;
        
            case 0xE1F2F3:  // HILL_TOP_LEFT
                return TileType.HILL_TOP_LEFT;
            case 0xF1F2F3:  // HILL_TOP_RIGHT
                return TileType.HILL_TOP_RIGHT;
            case 0x101112:  // HILL_BOTTOM_LEFT
                return TileType.HILL_BOTTOM_LEFT;
            case 0x111213:  // HILL_BOTTOM_RIGHT
                return TileType.HILL_BOTTOM_RIGHT;
        
            case 0x121314:  // RAVINE_TOP_LEFT
                return TileType.RAVINE_TOP_LEFT;
            case 0x131415:  // RAVINE_TOP_CENTER
                return TileType.RAVINE_TOP_CENTER;
            case 0x141516:  // RAVINE_TOP_RIGHT
                return TileType.RAVINE_TOP_RIGHT;
            case 0x151617:  // RAVINE_CENTER_LEFT
                return TileType.RAVINE_CENTER_LEFT;
            case 0x161718:  // RAVINE_CENTER_RIGHT
                return TileType.RAVINE_CENTER_RIGHT;
            case 0x171819:  // RAVINE_BOTTOM_LEFT
                return TileType.RAVINE_BOTTOM_LEFT;
            case 0x18191A:  // RAVINE_BOTTOM_CENTER
                return TileType.RAVINE_BOTTOM_CENTER;
            case 0x191A1B:  // RAVINE_BOTTOM_RIGHT
                return TileType.RAVINE_BOTTOM_RIGHT;
        
            case 0x202122:  // DIRT_WALL
                return TileType.DIRT_WALL;
            case 0x212223:  // SAND
                return TileType.SAND;
        
            case 0x222324:  // WATER
                return TileType.WATER;
        
            case 0x232425:  // WATER_RAVINE_TOP_LEFT
                return TileType.WATER_RAVINE_TOP_LEFT;
            case 0x242526:  // WATER_RAVINE_TOP_CENTER
                return TileType.WATER_RAVINE_TOP_CENTER;
            case 0x252627:  // WATER_RAVINE_TOP_RIGHT
                return TileType.WATER_RAVINE_TOP_RIGHT;
            case 0x262728:  // WATER_RAVINE_CENTER_LEFT
                return TileType.WATER_RAVINE_CENTER_LEFT;
            case 0x272829:  // WATER_RAVINE_CENTER_RIGHT
                return TileType.WATER_RAVINE_CENTER_RIGHT;
            case 0x28292A:  // WATER_RAVINE_BOTTOM_LEFT
                return TileType.WATER_RAVINE_BOTTOM_LEFT;
            case 0x292A2B:  // WATER_RAVINE_BOTTOM_CENTER
                return TileType.WATER_RAVINE_BOTTOM_CENTER;
            case 0x2A2B2C:  // WATER_RAVINE_BOTTOM_RIGHT
                return TileType.WATER_RAVINE_BOTTOM_RIGHT;
        
            case 0x2B2C2D:  // DIRT_ROAD_TOP_LEFT
                return TileType.DIRT_ROAD_TOP_LEFT;
            case 0x2C2D2E:  // DIRT_ROAD_TOP_CENTER
                return TileType.DIRT_ROAD_TOP_CENTER;
            case 0x2D2E2F:  // DIRT_ROAD_TOP_RIGHT
                return TileType.DIRT_ROAD_TOP_RIGHT;
            case 0x2E2F30:  // DIRT_ROAD_CENTER_LEFT
                return TileType.DIRT_ROAD_CENTER_LEFT;
            case 0x2F3031:  // DIRT_ROAD_CENTER
                return TileType.DIRT_ROAD_CENTER;
            case 0x303132:  // DIRT_ROAD_CENTER_RIGHT
                return TileType.DIRT_ROAD_CENTER_RIGHT;
            case 0x313233:  // DIRT_ROAD_BOTTOM_LEFT
                return TileType.DIRT_ROAD_BOTTOM_LEFT;
            case 0x323334:  // DIRT_ROAD_BOTTOM_CENTER
                return TileType.DIRT_ROAD_BOTTOM_CENTER;
            case 0x333435:  // DIRT_ROAD_BOTTOM_RIGHT
                return TileType.DIRT_ROAD_BOTTOM_RIGHT;
        
            default:
                return null;
        }
    }
}
