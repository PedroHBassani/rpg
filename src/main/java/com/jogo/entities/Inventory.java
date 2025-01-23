package com.jogo.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import com.jogo.graphics.SpriteSheet;

public class Inventory {

    private final List<String> items;
    private final BufferedImage inventorySprite;

    public Inventory() {
        items = new ArrayList<>();
        inventorySprite = new SpriteSheet("src/main/resources/menu/inventory.png").getSprite(0, 0, 48, 48, 1);
    }

    public void addItem(String item) {
        items.add(item);
    }

    public void removeItem(String item) {
        items.remove(item);
    }

    public List<String> getItems() {
        return items;
    }

    public void render(Graphics g, int screenWidth, int screenHeight) {
        int spriteSize = 48;
        int startX = 10;
        int startY = screenHeight - spriteSize - 10;

        for (int i = 0; i < 4; i++) {
            g.drawImage(inventorySprite, startX + i * spriteSize, startY, spriteSize, spriteSize, null);
        }

        SpriteSheet sheet = new SpriteSheet("src/main/resources/tiles/sprint_tiles.png");
        BufferedImage mushroom = sheet.getSprite(5, 3, 16, 16, 1);
        BufferedImage white_flower = sheet.getSprite(6, 3, 16, 16, 1);

        List<BufferedImage> itms = new ArrayList<>();
        itms.add(mushroom);
        itms.add(mushroom);
        itms.add(white_flower);

        for (int i = 0; i < itms.size(); i++) {
            int itemX = startX + i * spriteSize + (spriteSize - 36) / 2;
            int itemY = startY + (spriteSize - 35) / 2;
            g.drawImage(itms.get(i), itemX, itemY, 36, 35, null);
        }
    }
}
