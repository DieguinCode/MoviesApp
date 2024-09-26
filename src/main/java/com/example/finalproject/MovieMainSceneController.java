package com.example.finalproject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;

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
            } catch (Exception e) {

                MovieViewFX.showAlert("Um erro inesperado ocorreu!", AlertType.ERROR);
                e.printStackTrace();
    
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
