package com.example.finalproject.controllers;

import java.io.IOException;

import com.example.finalproject.MovieModel;
import com.example.finalproject.MovieViewFX;
import com.example.finalproject.views.MovieWatchedSceneView;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Alert.AlertType;

public class MovieWatchedSceneController {

    @FXML
    private Button backHomeButton;
    @FXML
    private Button previousButton;
    @FXML
    private Button nextButton;
    @FXML
    GridPane moviesGrid;

    private MovieWatchedSceneView view;

    @FXML
    public void initialize() {
        backHomeButton.setOnAction(e -> returnToInitialScene());
        previousButton.setOnAction(e -> view.showPreviousPage());
        nextButton.setOnAction(e -> view.showNextPage());
        previousButton.setDisable(true);

        VBox movieContainer;
        HBox movieOptionsContainer;
        Button removeButton;
        Button favoriteButton;

        for (int i = 0; i < moviesGrid.getChildren().size(); i++) {

            final int movieGridPosition = i;

            movieContainer = (VBox) moviesGrid.getChildren().get(i);
            movieOptionsContainer = (HBox) movieContainer.getChildren().get(3);

            removeButton = (Button) movieOptionsContainer.getChildren().get(0);
            removeButton.setOnAction(e -> removeMovie(movieGridPosition));

            favoriteButton = (Button) movieOptionsContainer.getChildren().get(1);
            favoriteButton.setOnAction(e -> favoriteMovie(movieGridPosition));

        }

    }

    public void setReferenceToView(MovieWatchedSceneView view) {
        this.view = view;
    }

    private void returnToInitialScene() {
        try {
            MovieViewFX.initialScene();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Button getPreviousButton () {
        return this.previousButton;
    }

    public Button getNextButton () {
        return this.nextButton;
    }

    public GridPane getGridPane () {
        return this.moviesGrid;
    }

    private void removeMovie (int movieGridPosition) {

        MovieModel.removeMovie(MovieModel.getWatchedMovies().get(movieGridPosition + view.getPageOffset()).id, "watched.txt");
        MovieModel.getWatchedMovies().remove(movieGridPosition + view.getPageOffset());
        view.updateView();

    }

    private void favoriteMovie (int movieGridPosition) {

        MovieModel.removeMovie(MovieModel.getWatchedMovies().get(movieGridPosition + view.getPageOffset()).id, "favorites.txt");
        MovieModel.addFavorite(MovieModel.getWatchedMovies().get(movieGridPosition + view.getPageOffset()));
        MovieViewFX.showAlert("Filme adicionado aos favoritos!", AlertType.CONFIRMATION);

    }

}
