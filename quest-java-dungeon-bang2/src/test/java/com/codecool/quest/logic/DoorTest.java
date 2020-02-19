package com.codecool.quest.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DoorTest {
    private GameMap gameMap = MapLoader.loadMap(MapLoader.currentMap);

    @Test
    void tryToMoveToClosedDoorCell() {
        gameMap.getCell(2, 1).setType(CellType.DOOR);
        Player player = new Player(gameMap.getCell(1, 1));
        player.checkCanMove(1, 0);

        assertEquals(player, gameMap.getCell(1, 1).getActor());
        assertNull(gameMap.getCell(2, 1).getActor());
    }

    @Test
    void tryToMoveToOpenedDoorCell() {
        gameMap.getCell(2, 1).setType(CellType.OPEN_DOOR);
        Player player = new Player(gameMap.getCell(1, 1));
        player.checkCanMove(1, 0);

        assertEquals(player, gameMap.getCell(2, 1).getActor());
        assertNull(gameMap.getCell(1, 1).getActor());
    }
}
