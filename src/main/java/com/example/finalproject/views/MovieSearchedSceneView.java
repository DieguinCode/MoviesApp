package com.example.finalproject.views;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.example.finalproject.CrucialSearchElements;
import com.example.finalproject.MovieModel;
import com.example.finalproject.MovieViewFX;
import com.example.finalproject.controllers.MovieFavoritesSceneController;
import com.example.finalproject.controllers.MovieSearchedSceneController;

public class MovieSearchedSceneView {

    private int currentIndex = 0;
    private int width = 600, height = 700;
    private Button previousButton;
    private Button nextButton;
    private ImageView moviePoster;
    private Label movieTitle;
    private Label movieActors;
    private Label movieYear;

    public void showScene (Stage stage) throws IOException {

        FXMLLoader loader = new FXMLLoader(MovieViewFX.class.getResource("searched-scene.fxml"));
        Parent root = loader.load();

        MovieSearchedSceneController controller = loader.getController();
        controller.setReferenceToView(this);
        previousButton = controller.getPreviousButton();
        nextButton = controller.getNextButton();
        moviePoster = controller.getMoviePoster();
        movieTitle = controller.getMovieTitle();
        movieActors = controller.getMovieActors();
        movieYear = controller.getMovieYear();

        updateView();

        Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.show();

    }

    private void updateView() {
        CrucialSearchElements movie = MovieModel.getLastSearchMovies().get(currentIndex);
        movieTitle.setText(movie.title);
        movieActors.setText("Actors: " + movie.actors);
        movieYear.setText("Year: " + movie.year);
        moviePoster.setImage(movie.image);
    }

    public void showPreviousMovie() {
        if (currentIndex > 0) {
            currentIndex--;
            updateView();
        }
    }

    public void showNextMovie() {
        if (currentIndex < MovieModel.getLastSearchMovies().size() - 1) {
            currentIndex++;
            updateView();
        }
    }

    public int getCurrentIndex () {

        return this.currentIndex;

    }

}
