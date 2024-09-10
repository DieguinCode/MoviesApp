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

    public static void searchedScene(List<CrucialSearchElements> crucialSearchElementsList) throws IOException{
        final int[] currentIndex = {0};

        FXMLLoader loader = new FXMLLoader(MovieViewFX.class.getResource("searched-scene.fxml"));
        Parent root = loader.load();
        MovieSearchedSceneController controller = loader.getController();

        //Update for the first Scene.
        controller.updateContent(crucialSearchElementsList.getFirst());

        Scene scene = new Scene(root);

        controller.getPreviousButton().setOnAction(e -> {
            if (currentIndex[0] > 0) {
                currentIndex[0]--;
                controller.updateContent(crucialSearchElementsList.get(currentIndex[0]));
                stage.show();
            }
        });

        controller.getNextButton().setOnAction(e -> {
            if (currentIndex[0] < crucialSearchElementsList.size() - 1) {
                currentIndex[0]++;
                controller.updateContent(crucialSearchElementsList.get(currentIndex[0]));
                stage.show();
            }
        });

        stage.setScene(scene);
        stage.show();
    }
}
