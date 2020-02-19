package com.codecool.quest.logic;

public class Sword extends Item {

    private boolean isInInventory = false;

    public Sword(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "sword";
    }

    public boolean isInInventory() {
        return isInInventory;
    }

    public void setInInventory(boolean inInventory) {
        isInInventory = inInventory;
    }
}


