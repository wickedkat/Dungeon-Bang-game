package com.codecool.quest.logic;

public class Elixir extends Item {
    private boolean isInInventory = false;

    public Elixir(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "elixir";
    }

    public boolean isInInventory() {
        return isInInventory;
    }

    public void setInInventory(boolean inInventory) {
        isInInventory = inInventory;
    }
}
