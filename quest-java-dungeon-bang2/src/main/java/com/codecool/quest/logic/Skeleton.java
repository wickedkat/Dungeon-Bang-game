package com.codecool.quest.logic;

public class Skeleton extends Actor {

    private boolean canMove = true;
    private String name = "Skeleton";

    public Skeleton(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }


    public boolean canMove() {
        return canMove;
    }

    public void canMove(boolean canMove) {
        this.canMove = canMove;
    }

    public boolean ifNextToPlayer(int playerX, int playerY, int skeletonX, int skeletonY) {
        return (skeletonX + 1 == playerX && skeletonY == playerY) || (skeletonX - 1 == playerX && skeletonY == playerY) || (skeletonY + 1 == playerY && skeletonX == playerX) || (skeletonY - 1 == playerY && skeletonX == playerX);
    }

    public String getName() {
        return name;
    }

    @Override
    public void checkCanMove(int dx, int dy) {
        if (isOnMap(dx, dy)) {
            Cell nextCell = super.getCell().getNeighbor(dx, dy);
            if (nextCell.isEmptyForSkeleton()) {
                move(nextCell);
    }
        }}

}