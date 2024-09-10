package com.example.strategypattern;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MovieViewFX {

    public static Stage stage;

    public static void searchScene() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(MovieViewFX.class.getResource("seach-scene.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void favoriteScene() throws IOException{
        //TODO
    }

    public static void recommendationScene() throws IOException{
        //TODO
    }

    public static void watchedScene() throws IOException{
        //TODO
    }

    public static void searchedScene(List<CrucialSearchElements> movieList) throws IOException{
        FXMLLoader loader = new FXMLLoader(MovieViewFX.class.getResource("searched-scene.fxml"));
        Parent root = loader.load();

        MovieSearchedSceneController controller = loader.getController();
        controller.setMovies(movieList);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
