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
    public static double width = 700;
    public static double height = 700;

    public static void initialScene() throws IOException {

        stage.widthProperty().addListener((obs, oldVal, newVal) -> {
            width = newVal.doubleValue();
        });

        stage.heightProperty().addListener((obs, oldVal, newVal) -> {
            height = newVal.doubleValue();
        });

        Parent root = FXMLLoader.load(Objects.requireNonNull(MovieViewFX.class.getResource("main-scene.fxml")));
        Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.setTitle("Movie App");
        stage.show();
    }

    public static void searchScene() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(MovieViewFX.class.getResource("seach-scene.fxml")));
        Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.show();
    }

    public static void favoriteScene(List<CrucialSearchElements> favoritesList) throws IOException{
        //TODO
    }

    public static void recommendationScene() throws IOException{
        //TODO
    }

    public static void watchedScene(List<CrucialSearchElements> watchedList) throws IOException{
        //TODO
        for(CrucialSearchElements crucialSearchElement : watchedList){
            System.out.println("Titulo: " + crucialSearchElement.title);
            System.out.println("Id: " + crucialSearchElement.id);
            System.out.println("ImageUrl: " + crucialSearchElement.imageUrl);
        }
    }

    public static void searchedScene(List<CrucialSearchElements> movieList) throws IOException{
        FXMLLoader loader = new FXMLLoader(MovieViewFX.class.getResource("searched-scene.fxml"));
        Parent root = loader.load();

        MovieSearchedSceneController controller = loader.getController();
        controller.setMovies(movieList);

        Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.show();
    }
}
