package com.jogo.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import com.jogo.graphics.SpriteSheet;
import com.jogo.interfaces.Item;

import lombok.Getter;

@Getter
public class Inventory {

    private final List<Item> items;
    private final BufferedImage inventorySprite;
    private final int inventorySize = 6;

    public Inventory() {
        items = new ArrayList<>();
        inventorySprite = new SpriteSheet("src/main/resources/menu/inventory.png").getSprite(0, 0, 48, 48, 1);
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public void render(Graphics g, int screenWidth, int screenHeight) {
        int spriteSize = 36;
        int startX = 10;
        int startY = screenHeight - spriteSize - 10;

        for (int i = 0; i < inventorySize; i++) {
            g.drawImage(inventorySprite, startX + i * spriteSize, startY, spriteSize, spriteSize, null);
        }

        for (int i = 0; i < items.size(); i++) {
            if (i >= inventorySize) {
                break;
            }
            int itemX = startX + i * spriteSize + (spriteSize - 36) / 2;
            int itemY = startY + (spriteSize - 35) / 2;
            g.drawImage(items.get(i).getSprite(), itemX, itemY, 36, 35, null);
        }
    }
}
