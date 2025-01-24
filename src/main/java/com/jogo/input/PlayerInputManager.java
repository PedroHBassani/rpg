package com.jogo.input;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.jogo.constants.PlayerConstants;
import com.jogo.entities.Player;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PlayerInputManager {

    private final Player player;
    private final Set<Integer> activeKeys = new HashSet<>();
    private final Map<Integer, Integer> oppositeKeys = new HashMap<>();

    {
        oppositeKeys.put(KeyEvent.VK_W, KeyEvent.VK_S);
        oppositeKeys.put(KeyEvent.VK_S, KeyEvent.VK_W);
        oppositeKeys.put(KeyEvent.VK_A, KeyEvent.VK_D);
        oppositeKeys.put(KeyEvent.VK_D, KeyEvent.VK_A);
    }

    public void updateDirection(int keyCode, boolean pressed) {
        if (pressed) {
            Integer oppositeKey = oppositeKeys.get(keyCode);
            if (oppositeKey != null && activeKeys.contains(oppositeKey)) {
                return;
            }

            activeKeys.add(keyCode);

            switch (keyCode) {
                case KeyEvent.VK_W:
                    player.setCurrentDirection(PlayerConstants.UP.getValue());
                    break;
                case KeyEvent.VK_S:
                    player.setCurrentDirection(PlayerConstants.DOWN.getValue());
                    break;
                case KeyEvent.VK_A:
                    player.setCurrentDirection(PlayerConstants.LEFT.getValue());
                    break;
                case KeyEvent.VK_D:
                    player.setCurrentDirection(PlayerConstants.RIGHT.getValue());
                    break;
            }
        } else {
            activeKeys.remove(keyCode);
            if (player.getCurrentDirection() != null) {
                player.setLastDirection(player.getCurrentDirection());
            }
            player.setCurrentDirection(null);
        }
    }
}
