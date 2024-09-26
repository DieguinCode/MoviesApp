package com.example.finalproject.views;

import com.example.finalproject.CrucialSearchElements;
import com.example.finalproject.MovieModel;
import com.example.finalproject.MovieViewFX;
import com.example.finalproject.controllers.MovieWatchedSceneController;

import java.io.IOException;
import java.util.List;

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

public class MovieWatchedSceneView {

    private int pageOffset = 0;

    private int width = 600, height = 700;
    private GridPane moviesGrid;
    private Button previousButton;
    private Button nextButton;

    public void showScene (Stage stage) throws IOException {

        FXMLLoader loader = new FXMLLoader(MovieViewFX.class.getResource("watched-scene.fxml"));
        Parent root = loader.load();

        MovieWatchedSceneController controller = loader.getController();
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
        Label movieGrade;
        HBox movieOptionsContainer;
        Button removeButton;
        Button favoriteButton;

        List<CrucialSearchElements> movies = MovieModel.getWatchedMovies();

        if (pageOffset == movies.size()) {

            showPreviousPage();

        }

        int i = 0;
        while (i < moviesGrid.getChildren().size()) {
            movieContainer = (VBox) moviesGrid.getChildren().get(i);
            moviePoster = (ImageView) movieContainer.getChildren().get(0);
            movieTitle = (Label) movieContainer.getChildren().get(1);
            movieGrade = (Label) movieContainer.getChildren().get(2);

            movieOptionsContainer = (HBox) movieContainer.getChildren().get(3);
            removeButton = (Button) movieOptionsContainer.getChildren().get(0);
            favoriteButton = (Button) movieOptionsContainer.getChildren().get(1);

            if ((i + pageOffset) < movies.size()) {
                movie = movies.get(i + pageOffset);
                moviePoster.setImage(movie.image);
                movieTitle.setText(movie.title);
                movieGrade.setText("Nota do filme: " + movie.rank.toString());

                removeButton.setVisible(true);
                removeButton.setDisable(false);
                removeButton.setManaged(true);

                favoriteButton.setVisible(true);
                favoriteButton.setDisable(false);
                favoriteButton.setManaged(true);
            } else {
                moviePoster.setImage(null);
                movieTitle.setText("");
                movieGrade.setText("");

                removeButton.setVisible(false);
                removeButton.setDisable(true);
                removeButton.setManaged(false);

                favoriteButton.setVisible(false);
                favoriteButton.setDisable(true);
                favoriteButton.setManaged(false);
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
        if (pageOffset + 4 >= MovieModel.getWatchedMovies().size()) {
            return;
        }
        pageOffset += 4;
        updateView();
    }

    public int getPageOffset () {

        return this.pageOffset;

    }

}
