package com.jogo;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.Timer;

import com.jogo.entities.Player;
import com.jogo.entities.Sword;
import com.jogo.graphics.Camera;
import com.jogo.graphics.UIRenderer;
import com.jogo.map.TileManager;

public class Game extends JPanel {
    
    private static final int TARGET_FPS = 60;
    private static final int UPDATE_INTERVAL = 1000 / TARGET_FPS;
    
    private final Player player;
    private final TileManager tileManager;
    private final Camera camera;
    private UIRenderer uiRenderer;

    public Game() {
        tileManager = new TileManager("src/main/resources/map/mapa.png");
        player = new Player(200, 200);
        camera = new Camera();
        setupUIRenderer();
        addKeyListener(player);
        setFocusable(true);
        requestFocusInWindow();
        initializeInventory();
        startGameLoop();
        addMouseListenerToInventory();
    }
    
    private void setupUIRenderer() {
        try {
            uiRenderer = new UIRenderer(player);
        } catch (IOException e) {
            System.err.println("Erro ao inicializar o UIRenderer: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void initializeInventory() {
        Sword sword = new Sword("Sword", 10, 1, 1, 100);
        player.getInventory().addItem(sword);
    }
    
    private void startGameLoop() {
        Timer timer = new Timer(UPDATE_INTERVAL, e -> update());
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

        renderGameWorld(g);
        renderUI(g);
    }

    private void renderGameWorld(Graphics g) {
        g.translate(-camera.getX(), -camera.getY());
        tileManager.render(g);
        player.render(g);
        g.translate(camera.getX(), camera.getY());
    }
    
    private void renderUI(Graphics g) {
        player.renderInventory(g, getWidth(), getHeight());
        uiRenderer.render(g, getWidth(), getHeight());
    }

    private void addMouseListenerToInventory() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                player.getInventory().handleMouseClick(e, getWidth(), getHeight());
            }
        });
    }
}
