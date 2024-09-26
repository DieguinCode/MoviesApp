package com.example.finalproject.controllers;

import java.io.IOException;
import java.util.Optional;

import com.example.finalproject.MovieModel;
import com.example.finalproject.MovieViewFX;
import com.example.finalproject.views.MovieRecommendationsSceneView;

import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MovieRecommendationsSceneController {

    @FXML
    private Button backHomeButton;
    @FXML
    private Button previousButton;
    @FXML
    private Button nextButton;

    @FXML
    GridPane moviesGrid;

    MovieRecommendationsSceneView view;

    @FXML
    public void initialize() {
        backHomeButton.setOnAction(e -> returnToInitialScene());
        previousButton.setOnAction(e -> view.showPreviousPage());
        nextButton.setOnAction(e -> view.showNextPage());
        previousButton.setDisable(true);

        VBox movieContainer;
        HBox movieOptionsContainer;
        Button interestsButton;
        Button watchedButton;

        for (int i = 0; i < moviesGrid.getChildren().size(); i++) {

            final int movieGridPosition = i;

            movieContainer = (VBox) moviesGrid.getChildren().get(i);
            movieOptionsContainer = (HBox) movieContainer.getChildren().get(2);

            interestsButton = (Button) movieOptionsContainer.getChildren().get(0);
            interestsButton.setOnAction(e -> interestsMovie(movieGridPosition));

            watchedButton = (Button) movieOptionsContainer.getChildren().get(1);
            watchedButton.setOnAction(e -> watchedMovie(movieGridPosition));

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

    private void returnToInitialScene() {
        try {
            MovieViewFX.initialScene();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setReferenceToView(MovieRecommendationsSceneView view) {
        this.view = view;
    }

    private void interestsMovie (int movieGridPosition) {

        MovieModel.removeMovie(MovieModel.getLastRecommendationsMovies().get(movieGridPosition + view.getPageOffset()).id, "interests.txt");
        MovieModel.addInterest(MovieModel.getLastRecommendationsMovies().get(movieGridPosition + view.getPageOffset()));
        MovieViewFX.showAlert("Filme adicionado a lista de interesses!", AlertType.INFORMATION);
        view.updateView();

    }

    private void watchedMovie (int movieGridPosition) {

        Optional<Integer> grade = MovieViewFX.askForReview();
        if (grade.isPresent()) {
            MovieModel.removeMovie(MovieModel.getLastRecommendationsMovies().get(movieGridPosition + view.getPageOffset()).id, "watched.txt");
            MovieModel.addWatched(grade.get(), MovieModel.getLastRecommendationsMovies().get(movieGridPosition + view.getPageOffset()));
            MovieViewFX.showAlert("O filme foi adicionado a lista de assistidos!", AlertType.INFORMATION);
        } else {
            MovieViewFX.showAlert("Ação cancelada! O filme não foi inserido em Assistidos.", AlertType.WARNING);
        }

    }

}
