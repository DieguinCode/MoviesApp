package com.example.finalproject.controllers;

import java.io.IOException;
import java.util.Optional;

import com.example.finalproject.MovieModel;
import com.example.finalproject.MovieViewFX;
import com.example.finalproject.views.MovieInterestsSceneView;

import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MovieInterestsSceneController {

    @FXML
    private Button backHomeButton;
    @FXML
    private Button previousButton;
    @FXML
    private Button nextButton;

    @FXML
    GridPane moviesGrid;

    private MovieInterestsSceneView view;

    @FXML
    public void initialize() {
        backHomeButton.setOnAction(e -> returnToInitialScene());
        previousButton.setOnAction(e -> view.showPreviousPage());
        nextButton.setOnAction(e -> view.showNextPage());
        previousButton.setDisable(true);

        VBox movieContainer;
        HBox movieOptionsContainer;
        Button removeButton;
        Button watchedButton;

        for (int i = 0; i < moviesGrid.getChildren().size(); i++) {

            final int movieGridPosition = i;

            movieContainer = (VBox) moviesGrid.getChildren().get(i);
            movieOptionsContainer = (HBox) movieContainer.getChildren().get(2);

            removeButton = (Button) movieOptionsContainer.getChildren().get(0);
            removeButton.setOnAction(e -> removeMovie(movieGridPosition));

            watchedButton = (Button) movieOptionsContainer.getChildren().get(1);
            watchedButton.setOnAction(e -> watchedMovie(movieGridPosition));

        }
    }

    public void setReferenceToView(MovieInterestsSceneView view) {
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

        MovieModel.removeMovie(MovieModel.getInterestsMovies().get(movieGridPosition + view.getPageOffset()).id, "interests.txt");
        MovieModel.getInterestsMovies().remove(movieGridPosition + view.getPageOffset());
        view.updateView();

    }

    private void watchedMovie (int movieGridPosition) {

        Optional<Integer> grade = MovieViewFX.askForReview();
        if (grade.isPresent()) {
            MovieModel.removeMovie(MovieModel.getInterestsMovies().get(movieGridPosition + view.getPageOffset()).id, "watched.txt");
            MovieModel.addWatched(grade.get(), MovieModel.getInterestsMovies().get(movieGridPosition + view.getPageOffset()));
            removeMovie(movieGridPosition);
            MovieViewFX.showAlert("O filme foi adicionado a lista de assistidos!", AlertType.INFORMATION);
            view.updateView();
        } else {
            MovieViewFX.showAlert("Ação cancelada! O filme não foi inserido em Assistidos.", AlertType.WARNING);
        }

    }

}
