package com.example.finalproject.controllers;

import java.io.IOException;
import java.util.List;

import com.example.finalproject.CrucialSearchElements;
import com.example.finalproject.MovieModel;
import com.example.finalproject.MovieViewFX;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;

public class MovieFavoritesSceneController {

    @FXML
    private Button backHomeButton;
    @FXML
    private Button previousButton;
    @FXML
    private Button nextButton;

    @FXML
    GridPane moviesGrid;

    private List<CrucialSearchElements> movies;
    private int pageOffset = 0;
    private String[] movieIDs = new String[4];

    @FXML
    public void initialize() {
        backHomeButton.setOnAction(e -> returnToInitialScene());
        previousButton.setOnAction(e -> showPreviousPage());
        nextButton.setOnAction(e -> showNextPage());
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

    public void setMovies(List<CrucialSearchElements> movies) {
        this.movies = movies;
        updateView();
    }

    private void returnToInitialScene() {
        try {
            MovieViewFX.initialScene();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showPreviousPage() {
        if (pageOffset <= 0) {
            return;
        }
        pageOffset -= 4;
        updateView();
    }

    private void showNextPage() {
        if (pageOffset + 4 >= movies.size()) {
            return;
        }
        pageOffset += 4;
        updateView();
    }

    private void updateView() {
        CrucialSearchElements movie;
        VBox movieContainer;
        ImageView moviePoster;
        Label movieTitle;
        HBox movieOptionsContainer;
        Button removeButton;

        int i = 0;
        while (i < moviesGrid.getChildren().size()) {
            movieContainer = (VBox) moviesGrid.getChildren().get(i);
            moviePoster = (ImageView) movieContainer.getChildren().get(0);
            movieTitle = (Label) movieContainer.getChildren().get(1);

            movieOptionsContainer = (HBox) movieContainer.getChildren().get(2);
            removeButton = (Button) movieOptionsContainer.getChildren().get(0);

            if ((i + pageOffset) < movies.size()) {
                movie = movies.get(i + pageOffset);
                moviePoster.setImage(movie.image);
                movieTitle.setText(movie.title);
                movieIDs[i] = movie.id;

                removeButton.setVisible(true);
                removeButton.setDisable(false);
                removeButton.setManaged(true);
            } else {
                moviePoster.setImage(null);
                movieTitle.setText("");
                movieIDs[i] = null;

                removeButton.setVisible(false);
                removeButton.setDisable(true);
                removeButton.setManaged(false);
            }
            i++;
        }
        previousButton.setDisable(pageOffset <= 0);
        nextButton.setDisable(pageOffset + 4 >= movies.size());
    }

    private void removeMovie (int movieGridPosition) {

        MovieModel.removeMovie(movieIDs[movieGridPosition], "favorites.txt");
        movies.remove(movieGridPosition + pageOffset);
        updateView();

    }

}
