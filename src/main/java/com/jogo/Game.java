package com.jogo;

import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.Timer;

import com.jogo.entities.Player;
import com.jogo.map.TileManager;

public class Game extends JPanel {

    private final Player player;
    private final TileManager tileManager;

    public Game() {
        tileManager = new TileManager(50, 37);
        player = new Player(200, 200);

        addKeyListener(player);
        setFocusable(true);
        requestFocusInWindow();

        Timer timer = new Timer(1000 / 60, e -> update());
        timer.start();
    }

    private void update() {
        player.update();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(java.awt.Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        tileManager.render(g);
        
        player.render(g);
    }
}
