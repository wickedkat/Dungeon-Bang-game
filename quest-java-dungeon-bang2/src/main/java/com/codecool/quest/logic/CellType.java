package com.codecool.quest.logic;

public enum CellType {
    EMPTY("empty"),
    FLOOR("floor"),
    WALL("wall"),
    SWORD("sword"),
    WATER("water"),
    WATER1("water1"),
    TREE("tree"),
    SKULL("skull"),
    GRAVE("grave"),
    DOOR("door"),
    EXITDOOR("exitdoor"),
    OPEN_DOOR("opened_door"),
    OPEN_EXIT("opened_exitdoor"),
    GRASS("grass"),
    FRAME1("frame1"),
    FRAME2("frame2"),
    FRAME3("frame3"),
    FRAME4("frame4"),
    FRAME5("frame5"),
    FRAME6("frame6"),
    FRAME7("frame7"),
    FRAME8("frame8"),
    EXIT("exit");



    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
