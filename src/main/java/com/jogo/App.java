package com.jogo;

import javax.swing.JFrame;

public class App {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Jogo 2D");
        Game game = new Game();

        int size = 16;
        int width = size * 50;
        int height = size * 37;

        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(game);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
