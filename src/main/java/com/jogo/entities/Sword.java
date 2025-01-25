package com.jogo.entities;

import com.jogo.constants.ItemType;
import com.jogo.interfaces.Item;

import lombok.Getter;

@Getter
public class Sword extends Item {

    private final int damage;
    private final int range;
    private final int attackSpeed;
    private final int durability;

    public Sword(String name, int damage, int range, int attackSpeed, int durability) {
        super(name, sheet.getSprite(0, 0, 16, 16, 1), ItemType.SWORD);

        this.damage = damage;
        this.range = range;
        this.attackSpeed = attackSpeed;
        this.durability = durability;
    }
}
