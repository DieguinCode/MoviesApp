package com.example.strategypattern;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;

public class MovieSearchedSceneController {

    @FXML
    private ImageView moviePoster;
    @FXML
    private Label movieTitle;
    @FXML
    private Label movieActors;
    @FXML
    private Label movieYear;
    @FXML
    private Label movieRank;
    @FXML
    private Button previousButton;
    @FXML
    private Button nextButton;

    private List<CrucialSearchElements> movies;
    private int currentIndex = 0;

    public void setMovies(List<CrucialSearchElements> movies) {
        this.movies = movies;
        updateView();
    }

    @FXML
    public void initialize() {
        previousButton.setOnAction(e -> showPreviousMovie());
        nextButton.setOnAction(e -> showNextMovie());
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
        movieRank.setText("Rank: " + movie.rank);
        moviePoster.setImage(movie.image);
    }
}
