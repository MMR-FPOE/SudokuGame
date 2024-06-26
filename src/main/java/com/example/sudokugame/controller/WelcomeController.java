package com.example.sudokugame.controller;

import com.example.sudokugame.view.GameStage;
import com.example.sudokugame.view.WelcomeStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Objects;


public class WelcomeController {
    @FXML
    private TextField playerName;

    /**
     * Method associated with a Button that start GameStage
     *
     * @param event         Event called when we push the button
     * @throws IOException  Error exception
     */
    @FXML
    void startGame(ActionEvent event) throws IOException{
        String getNickname = playerName.getText();
        if(Objects.equals(getNickname, "")){
            getNickname = "Player";
        }
        GameController controller = GameStage.getInstance().getGameController();
        controller.getPlayerNickname(getNickname);
        WelcomeStage.deleteInstance();
    }
}
