package com.example.strategypattern;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MovieMainSceneController {

    @FXML
    private Button favoriteButton;
    @FXML
    private Button searchButton;
    @FXML
    private Button recommendationButton;
    @FXML
    private Button watchedButton;

    //We don't have data to request to the model -> We call the View.
    @FXML
    public void initialize(){
        searchButton.setOnAction(_ -> {
            try {
                MovieViewFX.searchScene();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        favoriteButton.setOnAction(_ -> {
            try {
                MovieViewFX.favoriteScene();
            }
            catch (IOException e){
                throw new RuntimeException(e);
            }
        });
        recommendationButton.setOnAction(_ -> {
            try {
                MovieViewFX.recommendationScene();
            }
            catch (IOException e){
                throw new RuntimeException(e);
            }
        });
        watchedButton.setOnAction(_ -> {
            try {
                MovieViewFX.watchedScene();
            }
            catch (IOException e){
                throw new RuntimeException(e);
            }
        });
    }
}
