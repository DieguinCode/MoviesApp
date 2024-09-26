package com.example.finalproject.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import com.example.finalproject.CrucialSearchElements;
import com.example.finalproject.MovieModel;
import com.example.finalproject.MovieViewFX;
import com.example.finalproject.controllers.MovieRecommendationsSceneController;

public class MovieRecommendationsSceneView {

    private int pageOffset = 0;
    private int width = 600, height = 700;
    private GridPane moviesGrid;
    private Button previousButton;
    private Button nextButton;

    public void showScene (Stage stage) throws IOException {

        FXMLLoader loader = new FXMLLoader(MovieViewFX.class.getResource("recommendations-scene.fxml"));
        Parent root = loader.load();

        MovieRecommendationsSceneController controller = loader.getController();
        controller.setReferenceToView(this);
        moviesGrid = controller.getGridPane();
        previousButton = controller.getPreviousButton();
        nextButton = controller.getNextButton();
        updateView();

        Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.show();

    }

    public void updateView() {
        CrucialSearchElements movie;
        VBox movieContainer;
        ImageView moviePoster;
        Label movieTitle;
        HBox movieOptionsContainer;
        Button interestsButton;
        Button watchedButton;

        List<CrucialSearchElements> movies = MovieModel.getLastRecommendationsMovies();

        if (pageOffset == movies.size()) {

            showPreviousPage();

        }

        int i = 0;
        while (i < moviesGrid.getChildren().size()) {
            movieContainer = (VBox) moviesGrid.getChildren().get(i);
            moviePoster = (ImageView) movieContainer.getChildren().get(0);
            movieTitle = (Label) movieContainer.getChildren().get(1);

            movieOptionsContainer = (HBox) movieContainer.getChildren().get(2);
            interestsButton = (Button) movieOptionsContainer.getChildren().get(0);
            watchedButton = (Button) movieOptionsContainer.getChildren().get(1);

            if ((i + pageOffset) < movies.size()) {
                movie = movies.get(i + pageOffset);
                moviePoster.setImage(movie.image);
                movieTitle.setText(movie.title);

                interestsButton.setVisible(true);
                interestsButton.setDisable(false);
                interestsButton.setManaged(true);

                watchedButton.setVisible(true);
                watchedButton.setDisable(false);
                watchedButton.setManaged(true);
            } else {
                moviePoster.setImage(null);
                movieTitle.setText("");

                interestsButton.setVisible(false);
                interestsButton.setDisable(true);
                interestsButton.setManaged(false);

                watchedButton.setVisible(false);
                watchedButton.setDisable(true);
                watchedButton.setManaged(false);
            }
            i++;
        }
        previousButton.setDisable(pageOffset <= 0);
        nextButton.setDisable(pageOffset + 4 >= movies.size());
    }

    public void showPreviousPage() {
        if (pageOffset <= 0) {
            return;
        }
        pageOffset -= 4;
        updateView();
    }

    public void showNextPage() {
        if (pageOffset + 4 >= MovieModel.getLastRecommendationsMovies().size()) {
            return;
        }
        pageOffset += 4;
        updateView();
    }

    public int getPageOffset () {

        return this.pageOffset;

    }

}
