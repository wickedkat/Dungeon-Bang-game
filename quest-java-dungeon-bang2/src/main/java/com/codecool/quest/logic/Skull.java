package com.codecool.quest.logic;

public class Skull extends Item {
    public Skull(Cell cell) {
        super(cell);
    }


    @Override
    public String getTileName() {
        return "skull";
    }
}
