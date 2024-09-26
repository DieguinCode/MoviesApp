package com.example.finalproject.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.example.finalproject.CrucialSearchElements;
import com.example.finalproject.MovieModel;
import com.example.finalproject.MovieViewFX;
import com.example.finalproject.views.MovieSearchedSceneView;

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

    private MovieSearchedSceneView view;

    @FXML
    public void initialize() {
        backHomeButton.setOnAction(_ -> returnToInitialScene());
        previousButton.setOnAction(_ -> view.showPreviousMovie());
        nextButton.setOnAction(_ -> view.showNextMovie());
        watchedButton.setOnAction(_ -> watchedMovie());
        interestsButton.setOnAction(_ -> {
            MovieModel.removeMovie(MovieModel.getLastSearchMovies().get(view.getCurrentIndex()).id, "interests.txt");
            MovieModel.addInterest(MovieModel.getLastSearchMovies().get(view.getCurrentIndex()));
            MovieViewFX.showAlert("Filme adicionado a lista de interesses!", AlertType.INFORMATION);
        });
    }

    private void returnToInitialScene () {
        try {
            MovieViewFX.initialScene();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setReferenceToView(MovieSearchedSceneView view) {
        this.view = view;
    }

    public Button getPreviousButton () {
        return this.previousButton;
    }

    public Button getNextButton () {
        return this.nextButton;
    }

    public ImageView getMoviePoster () {
        return this.moviePoster;
    }

    public Label getMovieTitle () {
        return this.movieTitle;
    }

    public Label getMovieActors () {
        return this.movieActors;
    }

    public Label getMovieYear () {
        return this.movieYear;
    }


    private void watchedMovie () {

        Optional<Integer> grade = MovieViewFX.askForReview();
        if (grade.isPresent()) {
            MovieModel.addWatched(grade.get(), MovieModel.getLastSearchMovies().get(view.getCurrentIndex()));
        } else {
            MovieViewFX.showAlert("Ação cancelada! O filme não foi inserido em Assistidos.", AlertType.WARNING);
        }

    }
}
