package com.example.finalproject;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.example.finalproject.controllers.MovieFavoritesSceneController;
import com.example.finalproject.controllers.MovieInterestsSceneController;
import com.example.finalproject.controllers.MovieRecommendationsSceneController;
import com.example.finalproject.controllers.MovieSearchedSceneController;
import com.example.finalproject.controllers.MovieWatchedSceneController;

public class MovieViewFX {

    public static Stage stage;
    public static double width = 700;
    public static double height = 700;

    public static void initialScene() throws IOException {

        // stage.widthProperty().addListener((obs, oldVal, newVal) -> {
        //     width = newVal.doubleValue();
        // });

        // stage.heightProperty().addListener((obs, oldVal, newVal) -> {
        //     height = newVal.doubleValue();
        // });

        Parent root = FXMLLoader.load(Objects.requireNonNull(MovieViewFX.class.getResource("main-scene.fxml")));
        Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.setTitle("MovieLib");
        stage.show();
    }

    public static void searchScene() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(MovieViewFX.class.getResource("search-scene.fxml")));
        Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.show();
    }

    public static void favoriteScene(List<CrucialSearchElements> favoritesList) throws IOException {
        FXMLLoader loader = new FXMLLoader(MovieViewFX.class.getResource("favorites-scene.fxml"));
        Parent root = loader.load();

        MovieFavoritesSceneController controller = loader.getController();
        controller.setMovies(favoritesList);

        Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.show();
    }

    public static void recommendationScene(List<CrucialSearchElements> recommendationList) throws IOException {
        FXMLLoader loader = new FXMLLoader(MovieViewFX.class.getResource("recommendations-scene.fxml"));
        Parent root = loader.load();

        MovieRecommendationsSceneController controller = loader.getController();
        controller.setMovies(recommendationList);

        Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.show();
    }

    public static void interestsScene(List<CrucialSearchElements> interestsList) throws IOException {
        FXMLLoader loader = new FXMLLoader(MovieViewFX.class.getResource("interests-scene.fxml"));
        Parent root = loader.load();

        MovieInterestsSceneController controller = loader.getController();
        controller.setMovies(interestsList);

        Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.show();
    }

    public static void watchedScene(List<CrucialSearchElements> watchedList) throws IOException {
        FXMLLoader loader = new FXMLLoader(MovieViewFX.class.getResource("watched-scene.fxml"));
        Parent root = loader.load();

        MovieWatchedSceneController controller = loader.getController();
        controller.setMovies(watchedList);

        Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.show();
    }

    public static void searchedScene(List<CrucialSearchElements> movieList) throws IOException {
        FXMLLoader loader = new FXMLLoader(MovieViewFX.class.getResource("searched-scene.fxml"));
        Parent root = loader.load();

        MovieSearchedSceneController controller = loader.getController();
        controller.setMovies(movieList);

        Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());
        stage.setScene(scene);
        stage.show();
    }

    public static void showAlert (String message, AlertType alertType) {

        Alert alert = new Alert(alertType);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();

    }

    public static Optional<Integer> askForReview () {

        Optional<Integer> grade = Optional.empty();
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("MoviesLib");
        dialog.setHeaderText(null);
        dialog.setContentText("Entre com a nota do filme (0-10): ");
        boolean continueToAsk = true;

        while (continueToAsk) {

            try {

                Optional<String> result = dialog.showAndWait();

                if (result.isPresent()) {

                    grade = Optional.of(Integer.valueOf(Integer.parseInt(result.get())));

                }

                continueToAsk = false;

            } catch (NumberFormatException e) {

                MovieViewFX.showAlert("Entrada inválida! Entre com um número entre 0 e 10.", AlertType.WARNING);
                continueToAsk = true;

            } catch (Exception e) {

                MovieViewFX.showAlert("Um erro inesperado ocorreu!", AlertType.ERROR);
                e.printStackTrace();
                continueToAsk = false;

            }

        }

        return grade;

    }

}
