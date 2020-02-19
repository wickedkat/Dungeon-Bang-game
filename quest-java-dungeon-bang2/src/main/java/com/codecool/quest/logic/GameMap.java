package com.codecool.quest.logic;

import java.util.ArrayList;

public class GameMap {
    private int width;
    private int height;
    private Cell[][] cells;
    private Player player;
    private ArrayList<Skeleton> skeletons = new ArrayList<>();
    public static boolean isLevelFinished;

    public GameMap(int width, int height) {
        this.width = width;
        this.height = height;
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(this, x, y);
            }
        }
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public int getWidth() {
        return width;
    }



    public int getHeight() {
        return height;
    }

    public ArrayList<Skeleton> getSkeletons() {
        return skeletons;
    }

    public void setSkeletons(Skeleton skeleton) {
        skeletons.add(skeleton);
    }

    public boolean isLevelFinished() {
        return isLevelFinished;
    }

    public void setLevelFinished(boolean levelFinished) {
        isLevelFinished = levelFinished;
    }
}
