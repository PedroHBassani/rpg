package com.jogo.constants;

public enum TileType {
    TOP_GRASS_1(0, false),
    TOP_GRASS_2(1, false),
    TOP_GRASS_3(2, false),
    TOP_GRASS_4(3, false),
    DIRT_ROAD_TOP_RIGHT(4, false),
    DIRT_ROAD_TOP_CENTER(5, false),
    DIRT_ROAD_TOP_LEFT(6, false),
    DIRT_ROAD_CENTER_RIGHT(7, false),
    DIRT_ROAD_CENTER(8, false),
    DIRT_ROAD_CENTER_LEFT(9, false),
    DIRT_ROAD_BOTTOM_RIGHT(10, false),
    DIRT_ROAD_BOTTOM_CENTER(11, false),
    DIRT_ROAD_BOTTOM_LEFT(12, false);

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
