package com.example.strategypattern;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MoviesApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        MovieViewFX.stage = stage;

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main-scene.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Movie App");
        stage.show();
    }

    public static void main(String[] args) {launch();}
}