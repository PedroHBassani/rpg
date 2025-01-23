
package com.jogo.constants;

public enum PlayerConstants {
    UP("up"),
    DOWN("down"),
    LEFT("left"),
    RIGHT("right"),
    IDLE("idle"),
    WALKING("walking");

    private final String value;

    PlayerConstants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}