package com.jogo.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ItemType {
    SWORD(0, 1, true);

    private final int id;
    private final int maxStackSize;
    private final Boolean collectable;

}
