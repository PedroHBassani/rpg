package com.jogo;

import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.Timer;

import com.jogo.entities.Player;
import com.jogo.entities.Sword;
import com.jogo.graphics.Camera;
import com.jogo.map.TileManager;

public class Game extends JPanel {

    private final Player player;
    private final TileManager tileManager;
    private final Camera camera;

    public Game() {
        tileManager = new TileManager(50, 37);
        player = new Player(200, 200);
        camera = new Camera();

        addKeyListener(player);
        setFocusable(true);
        requestFocusInWindow();

        Sword sword = new Sword("Sword", 10, 1, 1, 100);
        player.getInventory().addItem(sword);

        Timer timer = new Timer(1000 / 60, e -> update());
        timer.start();
    }

    private void update() {
        player.update();
        camera.update(player, getWidth(), getHeight());
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(java.awt.Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.translate(-camera.getX(), -camera.getY());
        tileManager.render(g);
        player.render(g);
        g.translate(camera.getX(), camera.getY());

        player.renderInventory(g, getWidth(), getHeight());
    }
}
