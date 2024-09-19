package com.example.finalproject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

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
        watchedButton.setOnAction(_ -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("MoviesApp");
            dialog.setHeaderText("Watched");
            dialog.setContentText("Add a Grade (0-10): ");

            Optional<String> result = dialog.showAndWait();

            result.ifPresent(grade -> {
                try {
                    int g = Integer.parseInt(grade);
                    MovieModel.addWatched(g, movies.get(currentIndex));
                } catch (NumberFormatException ex) {
                    //TODO
                }
            });
        });
        interestsButton.setOnAction(_ -> {
            MovieModel.addInterest(movies.get(currentIndex));
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
}
