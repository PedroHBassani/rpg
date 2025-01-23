package com.jogo.constants;

public enum TileType {
    GRASS(0, false),
    DIRT(1, false),
    WATER(2, true);

    private final int id;
    private final boolean solid;

    TileType(int id, boolean solid) {
        this.id = id;
        this.solid = solid;
    }

    public int getId() {
        return id;
    }

    public boolean isSolid() {
        return solid;
    }
}
