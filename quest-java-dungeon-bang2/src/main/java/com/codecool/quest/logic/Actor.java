package com.codecool.quest.logic;


public abstract class Actor implements Drawable {

    private Cell cell;
    private int health = 10;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }


    public void checkCanMove(int dx, int dy) {
        if (isOnMap(dx, dy)) {
            Cell nextCell = cell.getNeighbor(dx, dy);
            if (nextCell.isEmpty()) {
                move(nextCell);
                exitToNextLevel(nextCell);

            }
        }
    }

    private void exitToNextLevel(Cell nextCell) {
        if (nextCell.getType().equals(CellType.EXIT)) {
            MapLoader.currentMap = "/map2.txt";
            GameMap.isLevelFinished = true;

        }
    }

    protected void move(Cell nextCell) {
        cell.setActor(null);
        nextCell.setActor(this);
        cell = nextCell;
    }


    protected boolean isOnMap(int dx, int dy) {
        GameMap map = cell.getGameMap();
        return dx + this.getX() < map.getWidth() && dy + this.getY() < map.getHeight()
                && dx + this.getX() >0 && dy + this.getY() >0;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }


}
