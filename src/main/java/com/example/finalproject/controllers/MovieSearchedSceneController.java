package com.example.finalproject.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.example.finalproject.CrucialSearchElements;
import com.example.finalproject.MovieModel;
import com.example.finalproject.MovieViewFX;

public class MovieSearchedSceneController {

    @FXML
    private Button backHomeButton;
    @FXML
    private ImageView moviePoster;
    @FXML
    private Label movieTitle;
    @FXML
    private Label movieActors;
    @FXML
    private Label movieYear;
    @FXML
    private Button previousButton;
    @FXML
    private Button nextButton;
    @FXML
    private Button watchedButton;
    @FXML
    private Button interestsButton;

    private List<CrucialSearchElements> movies;
    private int currentIndex = 0;

    public void setMovies(List<CrucialSearchElements> movies) {
        this.movies = movies;
        updateView();
    }

    @FXML
    public void initialize() {
        backHomeButton.setOnAction(_ -> returnToInitialScene());
        previousButton.setOnAction(_ -> showPreviousMovie());
        nextButton.setOnAction(_ -> showNextMovie());
        watchedButton.setOnAction(_ -> watchedMovie());
        interestsButton.setOnAction(_ -> {
            MovieModel.addInterest(movies.get(currentIndex));
            MovieViewFX.showAlert("Filme adicionado a lista de interesses!", AlertType.INFORMATION);
        });
    }

    private void returnToInitialScene () {
        try {
            MovieViewFX.initialScene();
        } catch (IOException e) {
            //TODO
        }
    }

    private void showPreviousMovie() {
        if (currentIndex > 0) {
            currentIndex--;
            updateView();
        }
    }

    private void showNextMovie() {
        if (currentIndex < movies.size() - 1) {
            currentIndex++;
            updateView();
        }
    }

    private void updateView() {
        CrucialSearchElements movie = movies.get(currentIndex);
        movieTitle.setText(movie.title);
        movieActors.setText("Actors: " + movie.actors);
        movieYear.setText("Year: " + movie.year);
        moviePoster.setImage(movie.image);
    }

    private void watchedMovie () {

        Optional<Integer> grade = MovieViewFX.askForReview();
        if (grade.isPresent()) {
            MovieModel.addWatched(grade.get(), movies.get(currentIndex));
        } else {
            MovieViewFX.showAlert("Ação cancelada! O filme não foi inserido em Assistidos.", AlertType.WARNING);
        }

    }
}
