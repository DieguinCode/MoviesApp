package com.example.strategypattern;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;


public class MovieSearchedSceneController {
    @FXML
    private ImageView imageView;
    @FXML
    private Label titleLabel;
    @FXML
    private Label yearLabel;
    @FXML
    private Label rankLabel;
    @FXML
    private Label actorsLabel;
    @FXML
    private Label categoryLabel;
    @FXML
    private Button nextButton;
    @FXML
    private Button previousButton;

    public void updateContent(CrucialSearchElements crucialElement){
        // Update the image
        imageView.setImage(crucialElement.image);

        // Update Labels
        titleLabel.setText("Title: " + crucialElement.title);
        yearLabel.setText("Year: " + crucialElement.year);
        rankLabel.setText("Rank: " + crucialElement.rank);
        actorsLabel.setText("Actors/Director: " + crucialElement.actors);
        categoryLabel.setText("Category: " + crucialElement.style);
    }

    public Button getPreviousButton() {
        return previousButton;
    }

    public Button getNextButton() {
        return nextButton;
    }

}
