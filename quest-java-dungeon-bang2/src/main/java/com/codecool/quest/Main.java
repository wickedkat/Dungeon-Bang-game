package com.codecool.quest;

import com.codecool.quest.logic.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main extends Application {
    GameMap map = MapLoader.loadMap(MapLoader.currentMap);
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();
    Label nameLabel = new Label();
    Button pickupButton = new Button("Pick up item");
    List<String> itemsList = new ArrayList<>();
    Utils utils = new Utils();
    boolean gameContinues;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        gameContinues = true;
        GridPane ui = new GridPane();
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));


        TextInputDialog dialog = new TextInputDialog("Admin");
        dialog.setTitle("Text Input Dialog");
        dialog.setHeaderText("Name of the player");
        dialog.setContentText("Please enter your name:");

        Optional<String> result = dialog.showAndWait();

        if (result.isPresent() && result.get().trim().length() != 0) {
            map.getPlayer().setNamePlayer(result.get());
            System.out.println("Your name: " + result.get());
        }


        ui.add(new Label("Name: "), 0, 0);
        ui.add(nameLabel, 1, 0);
        ui.add(new Label("Health: "), 0, 1);
        ui.add(healthLabel, 1, 1);
        pickupButton.setVisible(false);
        ui.add(pickupButton, 1, 2);

        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);

        primaryStage.setTitle("Codecool Quest");
        primaryStage.show();
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        if (gameContinues) {
            switch (keyEvent.getCode()) {
                // Skeleton checkCanMove while pressing the arrow
                case UP:
                    map.getPlayer().checkCanMove(0, -1);
                    openDoor(0, -1);
                    gameAction();
                    break;
                case DOWN:
                    map.getPlayer().checkCanMove(0, 1);
                    openDoor(0, 1);
                    gameAction();
                    break;
                case LEFT:
                    map.getPlayer().checkCanMove(-1, 0);
                    openDoor(-1, 0);
                    gameAction();
                    break;
                case RIGHT:
                    map.getPlayer().checkCanMove(1, 0);
                    openDoor(1, 0);
                    gameAction();

                    break;
            }
        }
    }

    private void gameAction() {
        moveSkeletons();
        handleCollision();
        checkGameOver();
        handlePickupButton();
        refresh();
        checkLevelFinished();
    }

    private void moveSkeletons() {
        for (Skeleton skeleton : map.getSkeletons()) {
            if (skeleton.canMove()) {
                int randomNumber = (int) Math.floor(Math.random() * 4);
                switch (randomNumber) {
                    case 0:
                        skeleton.checkCanMove(0, -1);
                        break;
                    case 1:
                        skeleton.checkCanMove(0, 1);
                        break;
                    case 2:
                        skeleton.checkCanMove(-1, 0);
                        break;
                    case 3:
                        skeleton.checkCanMove(1, 0);
                        break;
                    default:
                        throw new RuntimeException("Number must be from 0 to 3");
                }
            } else {
                skeleton.canMove(true);
            }

            int playerX = map.getPlayer().getX();
            int playerY = map.getPlayer().getY();
            int skeletonX = skeleton.getX();
            int skeletonY = skeleton.getY();

            if (skeleton.ifNextToPlayer(playerX, playerY, skeletonX, skeletonY)) {
                skeleton.canMove(false);
            }
        }
    }

    private void handleCollision() {
        drownPlayer();
        int playerX = map.getPlayer().getX();
        int playerY = map.getPlayer().getY();

        for (Skeleton skeleton : map.getSkeletons()) {
            int skeletonX = skeleton.getX();
            int skeletonY = skeleton.getY();

            if (playerX == skeletonX && playerY == skeletonY) {
                utils.playSound(utils.getCollisionSoundPath());
                addSkeletonDmg(skeleton);
                addPlayerDmg();

                if (skeleton.getHealth() <= 0) {
                    map.getSkeletons().remove(skeleton);
                    new Skull(map.getCell(skeletonX, skeletonY));
                    break;
                }
            }
        }
    }

    private void addSkeletonDmg(Skeleton skeleton) {
        // skeleton dmg 1-10
        /*int dmg = (int) Math.floor(Math.random() * 10) + 1;*/
        int dmg;
        if (itemsList.contains("sword")) {
            dmg = 5;
        } else {
            dmg = 2;

        }

        skeleton.setHealth(skeleton.getHealth() - dmg);


        // I cannot check whether player has sword in inventory.... :(
    }

    private void addPlayerDmg() {
        // player dmg 1-3
        int dmg = (int) Math.floor(Math.random() * 3) + 1;
        int healthBeforeHit = map.getPlayer().getHealth();
        /*int dmg = 2;
        int healthBeforeHit = map.getPlayer().getHealth();*/

        map.getPlayer().setHealth(healthBeforeHit - dmg);
        healthLabel.setText("" + map.getPlayer().getHealth());
    }

    private void drownPlayer() {
        if (map.getPlayer().getCell().isWater()) {
            map.getPlayer().setHealth(0);
        }
    }

    private void checkGameOver() {
        if (map.getPlayer().getHealth() <= 0) {
            utils.playSound(utils.getGameOverSoundPath());
            healthLabel.setFont(new Font(20));
            healthLabel.setText("Game over");
            gameContinues = false;
        }
    }

    private void handlePickupButton() {
        if (map.getPlayer().getCell().hasItem()
                && !map.getPlayer().getCell().getObject().getTileName().equals(CellType.OPEN_DOOR.getTileName())
                && !map.getPlayer().getCell().getObject().getTileName().equals(CellType.OPEN_EXIT.getTileName())) {
            pickupButton.setVisible(true);
            pickupButton.setOnAction(event -> {
                itemsList.add(map.getPlayer().getCell().getObject().getTileName());
                String objectName = map.getPlayer().getCell().getObject().getTileName();

                switch (objectName) {
                    case "sword":
                        new Sword(map.getCell(28, itemsList.size()));
                        break;
                    case "key":
                        new Key(map.getCell(28, itemsList.size()));
                        break;
                    case "exitkey":
                        new ExitKey(map.getCell(28, itemsList.size()));
                        break;
                    case "elixir":
                        int bonus = 3;
                        int healthBeforeBonus = map.getPlayer().getHealth();
                        map.getPlayer().setHealth(healthBeforeBonus + bonus);
                        healthLabel.setText("" + map.getPlayer().getHealth());
                        new Elixir(map.getCell(28, itemsList.size()));
                        removeItemFromInventory("elixir");





                }
                map.getPlayer().getCell().setObject(null);
                pickupButton.setVisible(false);
                refresh();
            });
        } else {
            pickupButton.setVisible(false);
        }
    }

    private void removeClosedDoor(int x, int y) {
        map.getPlayer().getCell().getNeighbor(x, y).setType(CellType.FLOOR);
        map.getPlayer().getCell().getNeighbor(x, y).setObject(null);
    }

    private void removeItemFromInventory(String key) {
        for (int i = 1; i <= itemsList.size(); i++) {
            if (map.getCell(28, i).getObject().getTileName().equals(key)) {
                map.getCell(28, i).setObject(null);
                itemsList.remove(key);
            }
        }
    }

    private void openDoor(int x, int y) {
        int doorX = map.getPlayer().getCell().getNeighbor(x, y).getX();
        int doorY = map.getPlayer().getCell().getNeighbor(x, y).getY();
        String nameDoor = map.getPlayer().getCell().getNeighbor(x, y).getTileName();

        if (itemsList.contains("key")
                && nameDoor.equals(CellType.DOOR.getTileName())) {
            removeClosedDoor(x, y);
            Door openedDoor = new Door(map.getCell(doorX, doorY));
            openedDoor.setOpened(true);
            removeItemFromInventory("key");

        } else if (itemsList.contains("exitkey")
                && nameDoor.equals(CellType.EXITDOOR.getTileName())) {
            removeClosedDoor(x, y);
            ExitDoor openedDoor = new ExitDoor(map.getCell(doorX, doorY));
            openedDoor.setOpened(true);
            removeItemFromInventory("exitkey");
        }
    }

    private void checkLevelFinished() {
        if (GameMap.isLevelFinished) {
            map = MapLoader.loadMap(MapLoader.currentMap);
            refresh();
            GameMap.isLevelFinished = false;
            itemsList.remove("sword");
        }
    }

    private void refresh() {
        if (gameContinues) {
            context.setFill(Color.BLACK);
            context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
            for (int x = 0; x < map.getWidth(); x++) {
                for (int y = 0; y < map.getHeight(); y++) {
                    Cell cell = map.getCell(x, y);
                    if (cell.getActor() != null) {
                        Tiles.drawTile(context, cell.getActor(), x, y);
                    } else if (cell.getObject() != null) {
                        Tiles.drawTile(context, cell.getObject(), x, y);

                    } else {
                        Tiles.drawTile(context, cell, x, y);
                    }
                }
                healthLabel.setText("" + map.getPlayer().getHealth());
                if (map.getPlayer().getNamePlayer() != null) {
                    nameLabel.setText("" + map.getPlayer().getNamePlayer());
                } else {
                    nameLabel.setText("No name");
                }
            }
        }
    }
}
