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
        MovieViewFX.initialScene();
    }

    public static void main(String[] args) {launch();}
}