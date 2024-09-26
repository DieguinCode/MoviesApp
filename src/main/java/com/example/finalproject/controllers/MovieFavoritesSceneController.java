package com.example.finalproject.controllers;

import java.io.IOException;

import com.example.finalproject.MovieModel;
import com.example.finalproject.MovieViewFX;
import com.example.finalproject.views.MovieFavoritesSceneView;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MovieFavoritesSceneController {

    @FXML
    private Button backHomeButton;
    @FXML
    private Button previousButton;
    @FXML
    private Button nextButton;

    @FXML
    GridPane moviesGrid;

    private MovieFavoritesSceneView view;

    @FXML
    public void initialize() {
        backHomeButton.setOnAction(e -> returnToInitialScene());
        previousButton.setOnAction(e -> view.showPreviousPage());
        nextButton.setOnAction(e -> view.showNextPage());
        previousButton.setDisable(true);

        VBox movieContainer;
        HBox movieOptionsContainer;
        Button removeButton;

        for (int i = 0; i < moviesGrid.getChildren().size(); i++) {

            final int movieGridPosition = i;

            movieContainer = (VBox) moviesGrid.getChildren().get(i);
            movieOptionsContainer = (HBox) movieContainer.getChildren().get(2);

            removeButton = (Button) movieOptionsContainer.getChildren().get(0);
            removeButton.setOnAction(e -> removeMovie(movieGridPosition));

        }
    }

    public void setReferenceToView(MovieFavoritesSceneView view) {
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

        MovieModel.removeMovie(MovieModel.getFavoritesMovies().get(movieGridPosition + view.getPageOffset()).id, "favorites.txt");
        MovieModel.getFavoritesMovies().remove(movieGridPosition + view.getPageOffset());
        view.updateView();

    }

}
