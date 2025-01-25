package com.jogo.entities;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
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
    private final BufferedImage selectedItemSprite;
    private final int inventorySize = 8;
    private int selectedItem = 0;

    public Inventory() {
        items = new ArrayList<>();
        inventorySprite = new SpriteSheet("src/main/resources/menu/item_inventory.png").getSprite(0, 0, 48, 48, 1);
        selectedItemSprite = new SpriteSheet("src/main/resources/menu/select_item_inventory.png").getSprite(0, 0, 48, 48, 1);
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public void render(Graphics g, int screenWidth, int screenHeight) {
        int spriteSize = 36;
        int startX = (screenWidth - (inventorySize * spriteSize)) / 2;
        int startY = screenHeight - spriteSize - 10;

        for (int i = 0; i < inventorySize; i++) {
            if (i == selectedItem) {
                g.drawImage(selectedItemSprite, startX + i * spriteSize, startY, spriteSize, spriteSize, null);
            } else {
                g.drawImage(inventorySprite, startX + i * spriteSize, startY, spriteSize, spriteSize, null);
            }
        }

        for (int i = 0; i < items.size(); i++) {
            if (i >= inventorySize) {
                break;
            }
            int itemX = startX + i * spriteSize + (spriteSize - 36) / 2;
            int itemY = startY + (spriteSize - 35) / 2;
            BufferedImage itemSprite = items.get(i).getSprite();
            int itemWidth = itemSprite.getWidth();
            int itemHeight = itemSprite.getHeight();

            if (itemWidth >= 36 || itemHeight >= 35) {
                float widthRatio = 36.0f / itemWidth;
                float heightRatio = 35.0f / itemHeight;
                float ratio = Math.min(widthRatio, heightRatio);
                int newWidth = (int) (itemWidth * ratio);
                int newHeight = (int) (itemHeight * ratio);
                g.drawImage(itemSprite, itemX, itemY, newWidth, newHeight, null);
            } else {
                g.drawImage(itemSprite, itemX, itemY, 36, 35, null);
            }
        }
    }

    public void handleMouseClick(MouseEvent e, int screenWidth, int screenHeight) {
        int spriteSize = 36;
        int startX = (screenWidth - (inventorySize * spriteSize)) / 2;
        int startY = screenHeight - spriteSize - 10;
        int mouseX = e.getX();
        int mouseY = e.getY();

        if (mouseY >= startY && mouseY <= startY + spriteSize) {
            for (int i = 0; i < inventorySize; i++) {
                int itemX = startX + i * spriteSize;
                if (mouseX >= itemX && mouseX <= itemX + spriteSize) {
                    selectedItem = i;
                    break;
                }
            }
        }
    }
}
