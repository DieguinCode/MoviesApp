package com.example.finalproject.controllers;

import com.example.finalproject.CrucialSearchElements;
import com.example.finalproject.MovieModel;
import com.example.finalproject.MovieViewFX;
import com.example.finalproject.MoviesApp;
import com.example.finalproject.views.MovieFavoritesSceneView;
import com.example.finalproject.views.MovieInterestsSceneView;
import com.example.finalproject.views.MovieRecommendationsSceneView;
import com.example.finalproject.views.MovieSearchSceneView;
import com.example.finalproject.views.MovieSearchedSceneView;
import com.example.finalproject.views.MovieWatchedSceneView;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;

import java.util.List;

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

                new MovieSearchSceneView().showScene(MoviesApp.stage);

            } catch (Exception e) {

                MovieViewFX.showAlert("Um erro inesperado ocorreu!", AlertType.ERROR);
                e.printStackTrace();
    
            }
        });
        favoriteButton.setOnAction(_ -> {
            try {

                new MovieFavoritesSceneView().showScene(MoviesApp.stage);

            } catch (Exception e) {

                MovieViewFX.showAlert("Um erro inesperado ocorreu!", AlertType.ERROR);
                e.printStackTrace();

            }
        });
        recommendationButton.setOnAction(_ -> {
           try {

                List<CrucialSearchElements> movies = MovieModel.getRecommendations();
                if (movies.isEmpty()) {

                    MovieViewFX.showAlert("Nenhuma recomendação foi encontrada!", AlertType.WARNING);

                } else if (movies == null) {

                    MovieViewFX.showAlert("Um erro inesperado ocorreu na recomendação!", AlertType.ERROR);

                } else {

                    try {

                        new MovieRecommendationsSceneView().showScene(MoviesApp.stage);

                    } catch (Exception e) {

                        MovieViewFX.showAlert("Um erro inesperado ocorreu na recomendação!", AlertType.ERROR);
                        e.printStackTrace();

                    }

                }

            } catch (Exception e) {

                MovieViewFX.showAlert("Um erro inesperado ocorreu!", AlertType.ERROR);
                e.printStackTrace();

            }
        });
        watchedButton.setOnAction(_ -> {
            try {

                new MovieWatchedSceneView().showScene(MoviesApp.stage);

            } catch (Exception e) {

                MovieViewFX.showAlert("Um erro inesperado ocorreu!", AlertType.ERROR);
                e.printStackTrace();

            }
        });
        interestsButton.setOnAction(_ -> {
            try {

                new MovieInterestsSceneView().showScene(MoviesApp.stage);

            } catch (Exception e) {

                MovieViewFX.showAlert("Um erro inesperado ocorreu!", AlertType.ERROR);
                e.printStackTrace();

            }
        });
    }

}
