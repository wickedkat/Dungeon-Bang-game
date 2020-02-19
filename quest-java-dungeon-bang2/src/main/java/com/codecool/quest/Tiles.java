package com.codecool.quest;

import com.codecool.quest.logic.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.awt.geom.AffineTransform;

import java.util.HashMap;
import java.util.Map;

public class Tiles {
    public static int TILE_WIDTH = 32;

    private static Image tileset = new Image("/tiles.png", 543 * 2, 543 * 2, true, false);
    private static Map<String, Tile> tileMap = new HashMap<>();

    public static class Tile {
        public final int x, y, w, h;
        Tile(int i, int j) {
            x = i * (TILE_WIDTH + 2);
            y = j * (TILE_WIDTH + 2);
            w = TILE_WIDTH;
            h = TILE_WIDTH;
        }
    }

    static {
        tileMap.put("empty", new Tile(0, 0));
        tileMap.put("wall", new Tile(6, 13));
        tileMap.put("floor", new Tile(1, 0));
        tileMap.put("player", new Tile(27, 0));
        tileMap.put("skeleton", new Tile(29, 6));
        tileMap.put("sword", new Tile(0, 30));
        tileMap.put("key", new Tile(16,23));
        tileMap.put("exitkey", new Tile(17,23));
        tileMap.put("door", new Tile(7, 9));
        tileMap.put("opened_door", new Tile(6, 9));
        tileMap.put("water", new Tile(8, 4));
        tileMap.put("water1", new Tile(12, 4));
        tileMap.put("tree", new Tile (1, 1));
        tileMap.put("skull", new Tile (0, 15));
        tileMap.put("grass", new Tile (5, 0));
        tileMap.put("grave", new Tile (1, 14));
        tileMap.put("exitdoor", new Tile(1, 9));
        tileMap.put("opened_exitdoor", new Tile(2, 9));
        tileMap.put("frame1", new Tile( 18,0));
        tileMap.put("frame2", new Tile(19, 0));
        tileMap.put("frame3", new Tile(20, 0));
        tileMap.put("frame4", new Tile(18, 1));
        tileMap.put("frame5", new Tile(20, 1));
        tileMap.put("frame6", new Tile(18, 2));
        tileMap.put("frame7", new Tile(19, 2));
        tileMap.put("frame8", new Tile(20, 2));
        tileMap.put("exit", new Tile (3,0));
        tileMap.put("elixir", new Tile ( 16,30));


    }

    public static void drawTile(GraphicsContext context, Drawable d, int x, int y) {
        Tile tile = tileMap.get(d.getTileName());
        context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
                x * TILE_WIDTH, y * TILE_WIDTH, TILE_WIDTH, TILE_WIDTH);
    }
}
