package com.example.sudokugame;

import com.example.sudokugame.view.WelcomeStage;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Game Controller Class
 *
 * @author Monica Cortes
 * @author Ricardo Zapata
 * @author Mauricio Teherán
 */
public class Main extends Application  {
    public static void main(String[] args) { launch(args);}

    @Override
    public void start(Stage primaryStage) throws IOException {
        WelcomeStage.getInstance();
    }
}