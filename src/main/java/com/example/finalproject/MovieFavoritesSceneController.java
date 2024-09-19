package com.example.finalproject;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MovieFavoritesSceneController {

    @FXML
    private Button backHomeButton;

    public void initialize() {
        backHomeButton.setOnAction(e -> returnToInitialScene());
    }

    private void returnToInitialScene () {
        try {
            MovieViewFX.initialScene();
        } catch (IOException e) {
            //TODO
        }
    }
    
}
