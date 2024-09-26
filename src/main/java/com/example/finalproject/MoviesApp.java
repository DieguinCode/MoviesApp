package com.example.finalproject;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class MoviesApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        MovieViewFX.stage = stage;
        MovieViewFX.initialScene();

        stage.minHeightProperty().set(700);
        stage.minWidthProperty().set(600);

    }

    public static void main(String[] args) {
        launch();
    }
}