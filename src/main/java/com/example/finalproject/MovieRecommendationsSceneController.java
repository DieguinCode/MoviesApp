package com.example.finalproject;

import java.io.IOException;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;

public class MovieRecommendationsSceneController {

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

    @FXML
    public void initialize() {
        backHomeButton.setOnAction(e -> returnToInitialScene());
        previousButton.setOnAction(e -> showPreviousPage());
        nextButton.setOnAction(e -> showNextPage());
        previousButton.setDisable(true);
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
        Label movieGrade;

        int i = 0;
        while (i < moviesGrid.getChildren().size()) {
            movieContainer = (VBox) moviesGrid.getChildren().get(i);
            moviePoster = (ImageView) movieContainer.getChildren().get(0);
            movieTitle = (Label) movieContainer.getChildren().get(1);
            movieGrade = (Label) movieContainer.getChildren().get(2);

            if ((i + pageOffset) < movies.size()) {
                movie = movies.get(i + pageOffset);
                String rank = (movie.rank != null) ? movie.rank.toString() : "---";
                
                moviePoster.setImage(movie.image);
                movieTitle.setText(movie.title);
                movieGrade.setText("Nota do filme: " + rank);
            } else {
                moviePoster.setImage(null);
                movieTitle.setText("");
                movieGrade.setText("");
            }
            i++;
        }
        previousButton.setDisable(pageOffset <= 0);
        nextButton.setDisable(pageOffset + 4 >= movies.size());
    }
}
