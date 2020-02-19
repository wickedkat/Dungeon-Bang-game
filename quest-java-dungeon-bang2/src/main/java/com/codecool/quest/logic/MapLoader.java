package com.codecool.quest.logic;

import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {
    public static String currentMap = "/map.txt";


    public static GameMap loadMap(String currentMap) {
        InputStream is = MapLoader.class.getResourceAsStream(currentMap);
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
                        case '#':
                            cell.setType(CellType.WALL);
                            break;
                        case '.':
                            cell.setType(CellType.FLOOR);
                            break;
                        case '-':
                            cell.setType(CellType.GRASS);
                            break;
                        case 's':
                            cell.setType(CellType.FLOOR);
                            map.setSkeletons(new Skeleton(cell));
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR);
                            map.setPlayer(new Player(cell));
                            break;
                        case '+':
                            cell.setType(CellType.FLOOR);
                            new Sword(cell);
                            break;
                        case 'k':
                            cell.setType(CellType.FLOOR);
                            new Key(cell);
                            break;
                        case '^':
                            cell.setType(CellType.SKULL);
                            break;
                        case 'X':
                            cell.setType(CellType.GRAVE);
                            break;
                        case 'w':
                            cell.setType(CellType.WATER);
                            break;
                        case 'W':
                            cell.setType(CellType.WATER1);
                            break;
                        case 'T':
                            cell.setType(CellType.TREE);
                            break;
                        case 'e':
                            cell.setType(CellType.DOOR);
                            new Door(cell);
                            break;
                        case '*':
                            cell.setType(CellType.EXITDOOR);
                            new ExitDoor(cell);
                            break;
                        case '=':
                            cell.setType(CellType.FLOOR);
                            new ExitKey(cell);
                            break;
                        case '1':
                            cell.setType(CellType.FRAME1);
                            break;
                        case '2':
                            cell.setType(CellType.FRAME2);
                            break;
                        case '3':
                            cell.setType(CellType.FRAME3);
                            break;
                        case '4':
                            cell.setType(CellType.FRAME4);
                            break;
                        case '5':
                            cell.setType(CellType.FRAME5);
                            break;
                        case '6':
                            cell.setType(CellType.FRAME6);
                            break;
                        case '7':
                            cell.setType(CellType.FRAME7);
                            break;
                        case '8':
                            cell.setType(CellType.FRAME8);
                            break;
                        case 'x':
                            cell.setType(CellType.EXIT);
                            break;
                        case 'F':
                            cell.setType(CellType.FLOOR);
                            new Elixir(cell);
                            break;


                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }

    public static String getCurrentMap() {
        return currentMap;
    }

    public void setCurrentMap(String currentMap) {
        this.currentMap = currentMap;
    }


}
