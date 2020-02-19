package com.codecool.quest.logic;

public class Player extends Actor {
    private static String namePlayer;

    public String getNamePlayer() {
        return namePlayer;
    }

    public void setNamePlayer(String namePlayer) {
        this.namePlayer = namePlayer;
    }

    public Player(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "player";
    }
}
