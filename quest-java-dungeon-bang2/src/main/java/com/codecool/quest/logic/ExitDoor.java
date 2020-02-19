package com.codecool.quest.logic;

public class ExitDoor extends Item {
    private boolean isOpened;

    public ExitDoor(Cell cell) {
        super(cell);
    }

    public void setOpened(boolean opened) {
        isOpened = opened;
    }

    public boolean getIsOpened() {
        return isOpened;
    }


    public void openDoor(ExitKey exitKey) {
        if (exitKey.getIsInInventory()) {
            isOpened = true;
            exitKey.setIsInInventory(false);
        }
    }

    @Override
    public String getTileName() {
        if (isOpened) {
            return "opened_exitdoor";
        }
        return "exitdoor";
    }
}
