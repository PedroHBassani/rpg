package com.jogo.constants;

public enum TileType {
    TOP_GRASS_1(0, false),
    TOP_GRASS_2(1, false),
    TOP_GRASS_3(2, false),
    TOP_GRASS_4(3, false),

    DIRT_TOP_RIGHT(4, false),
    DIRT_TOP_CENTER(5, false),
    DIRT_TOP_LEFT(6, false),
    DIRT_CENTER_RIGHT(7, false),
    DIRT_CENTER(8, false),
    DIRT_CENTER_LEFT(9, false),
    DIRT_BOTTOM_RIGHT(10, false),
    DIRT_BOTTOM_CENTER(11, false),
    DIRT_BOTTOM_LEFT(12, false),

    HILL_TOP_LEFT(13, false),
    HILL_TOP_RIGHT(14, false),
    HILL_BOTTOM_LEFT(15, false),
    HILL_BOTTOM_RIGHT(16, false),

    RAVINE_TOP_LEFT(17, false),
    RAVINE_TOP_CENTER(18, false),
    RAVINE_TOP_RIGHT(19, false),
    RAVINE_CENTER_LEFT(20, false),
    RAVINE_CENTER_RIGHT(21, false),
    RAVINE_BOTTOM_LEFT(22, false),
    RAVINE_BOTTOM_CENTER(23, false),
    RAVINE_BOTTOM_RIGHT(24, false),

    DIRT_WALL(25, true),
    SAND(26, false),
    RED_MUSHROOM(27, false),
    YELLOW_FLOWER(28, false),
    WHITE_FLOWER(29, false),
    GRASS(30, false),
    
    WATER(31, false),

    WATER_RAVINE_TOP_LEFT(32, false),
    WATER_RAVINE_TOP_CENTER(33, false),
    WATER_RAVINE_TOP_RIGHT(34, false),
    WATER_RAVINE_CENTER_LEFT(35, false),
    WATER_RAVINE_CENTER_RIGHT(36, false),
    WATER_RAVINE_BOTTOM_LEFT(37, false),
    WATER_RAVINE_BOTTOM_CENTER(38, false),
    WATER_RAVINE_BOTTOM_RIGHT(39, false),

    DIRT_ROAD_TOP_LEFT(40, false),
    DIRT_ROAD_TOP_CENTER(41, false),
    DIRT_ROAD_TOP_RIGHT(42, false),
    DIRT_ROAD_CENTER_LEFT(43, false),
    DIRT_ROAD_CENTER(44, false),
    DIRT_ROAD_CENTER_RIGHT(45, false),
    DIRT_ROAD_BOTTOM_LEFT(46, false),
    DIRT_ROAD_BOTTOM_CENTER(47, false),
    DIRT_ROAD_BOTTOM_RIGHT(48, false);

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
