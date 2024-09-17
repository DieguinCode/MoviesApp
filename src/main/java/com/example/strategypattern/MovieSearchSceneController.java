package com.example.strategypattern;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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
            //TODO
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
        MovieModel.autoComplete(final_result);
    }
}
