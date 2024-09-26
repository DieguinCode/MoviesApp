package com.example.finalproject.controllers;

import java.io.IOException;
import java.util.List;

import com.example.finalproject.MovieModel;
import com.example.finalproject.MovieViewFX;
import com.example.finalproject.MoviesApp;
import com.example.finalproject.views.MovieSearchedSceneView;
import com.example.finalproject.CrucialSearchElements;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class MovieSearchSceneController {

    @FXML
    private Button backHomeButton;
    @FXML
    private TextField searchField;
    @FXML
    private Button enterButton;

    @FXML
    public void initialize(){
        backHomeButton.setOnAction(e -> returnToInitialScene());
        enterButton.setOnAction(_ -> {
            handleSearch();
        });
        searchField.setOnAction(_ -> {
            handleSearch();
        });
    }

    private void returnToInitialScene () {
        try {
            MovieViewFX.initialScene();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleSearch(){
        String userInput = searchField.getText().toLowerCase();
        String[] words = userInput.split(" ");
        StringBuilder search = new StringBuilder();

        for(int i = 0; i < words.length; i++){
            search.append(words[i]);
            if(i < words.length - 1){
                search.append("%20");
            }
        }

        String final_result = search.toString();
        List<CrucialSearchElements> movies = MovieModel.autoComplete(final_result);
        if (movies.isEmpty()) {

            MovieViewFX.showAlert("Nenhum resultado foi encontrado!", AlertType.WARNING);

        } else if (movies == null) {

            MovieViewFX.showAlert("Um erro inesperado ocorreu na busca!", AlertType.ERROR);

        } else {

            try {

                new MovieSearchedSceneView().showScene(MoviesApp.stage);

            } catch (Exception e) {

                MovieViewFX.showAlert("Um erro inesperado ocorreu na busca!", AlertType.ERROR);
                e.printStackTrace();

            }

        }
    }
}
