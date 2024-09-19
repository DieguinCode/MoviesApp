package com.example.finalproject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

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
    @FXML
    private Button interestsButton;

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
            MovieModel.getFavorites();
        });
        recommendationButton.setOnAction(_ -> {
           MovieModel.getRecommendations();
        });
        watchedButton.setOnAction(_ -> {
            MovieModel.getWatched();
        });
        interestsButton.setOnAction(_ -> {
            MovieModel.getInterests();
        });
    }
}
