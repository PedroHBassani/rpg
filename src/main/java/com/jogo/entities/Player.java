package com.jogo.entities;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.jogo.animation.PlayerAnimationManager;
import com.jogo.constants.PlayerConstants;
import com.jogo.input.PlayerInputManager;
import com.jogo.interfaces.Item;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class Player implements KeyListener {

    private final PlayerAnimationManager animationManager;
    private final PlayerInputManager inputManager;
    private final int speed = 10;
    private final Inventory inventory;
    private int health;
    private final int maxHealth = 100;
    private int coins;

    private int x, y;
    private String currentDirection = null;
    private String lastDirection = PlayerConstants.DOWN.getValue();
    private boolean isMoving = false;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        animationManager = new PlayerAnimationManager();
        inputManager = new PlayerInputManager(this);
        inventory = new Inventory();
        this.health = maxHealth;
        this.coins = 0;

        animationManager.setSpriteSheet("src/main/resources/character/idle.png", PlayerConstants.IDLE.getValue(), 7);
        animationManager.setSpriteSheet("src/main/resources/character/walk.png", PlayerConstants.WALKING.getValue(), 2);

        animationManager.setCurrentAnimation(PlayerConstants.IDLE.getValue(), lastDirection);
    }

    public void update() {
        if (currentDirection != null) {
            isMoving = true;
            movePlayer();
            animationManager.setCurrentAnimation(PlayerConstants.WALKING.getValue(), currentDirection);
        } else {
            isMoving = false;
            animationManager.setCurrentAnimation(PlayerConstants.IDLE.getValue(), lastDirection);
            animationManager.updateCurrentAnimation();
        }
        if (isMoving) {
            animationManager.updateCurrentAnimation();
        }
    }

    private void movePlayer() {
        switch (currentDirection) {
            case "up":
                y -= speed;
                break;
            case "down":
                y += speed;
                break;
            case "left":
                x -= speed;
                break;
            case "right":
                x += speed;
                break;
        }
    }

    public void render(Graphics g) {
        g.drawImage(animationManager.getCurrentFrame(), x, y, null);
    }

    public void renderInventory(Graphics g, int screenWidth, int screenHeight) {
        inventory.render(g, screenWidth, screenHeight);
    }

    public void addItemToInventory(Item item) {
        inventory.addItem(item);
    }

    public void removeItemFromInventory(Item item) {
        inventory.removeItem(item);
    }

    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health < 0) {
            this.health = 0;
        }
    }

    public void heal(int amount) {
        this.health += amount;
        if (this.health > maxHealth) {
            this.health = maxHealth;
        }
    }

    public boolean isAlive() {
        return this.health > 0;
    }

    public void addCoins(int amount) {
        this.coins += amount;
    }

    public void removeCoins(int amount) {
        this.coins -= amount;
        if (this.coins < 0) {
            this.coins = 0;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        inputManager.updateDirection(e.getKeyCode(), true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        inputManager.updateDirection(e.getKeyCode(), false);
    }
}
