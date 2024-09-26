package com.example.finalproject.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import com.example.finalproject.MovieViewFX;

public class MovieSearchSceneView {

    private int width = 600, height = 700;
    
    public void showScene (Stage stage) throws IOException {

        Parent root = FXMLLoader.load(MovieViewFX.class.getResource("search-scene.fxml"));
        Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.show();

    }

}
