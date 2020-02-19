package com.codecool.quest;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

public class Utils {

    private String collisionSoundPath = "src/main/resources/snd/collsionSound.wav";
    private String gameOverSoundPath = "src/main/resources/snd/gameOver.wav";

    public void playSound(String pathToFile) {
        try {
            Media sound = new Media(new File(pathToFile).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.play();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String getCollisionSoundPath() {
        return collisionSoundPath;
    }

    public String getGameOverSoundPath() {
        return gameOverSoundPath;
    }
}
