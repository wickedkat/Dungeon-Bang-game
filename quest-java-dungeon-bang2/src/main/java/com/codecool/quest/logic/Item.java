package com.codecool.quest.logic;

public abstract class Item implements Drawable {

    private Cell cell;


    public Item(Cell cell) {
        this.cell = cell;
        this.cell.setObject(this);
    }
    
    public Cell getCell () {
        return cell;
    }
}
