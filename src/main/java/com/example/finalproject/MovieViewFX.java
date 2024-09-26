package com.example.finalproject;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class MovieViewFX {

    public static double width = 600;
    public static double height = 700;

    public static void initialScene() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(MovieViewFX.class.getResource("main-scene.fxml")));
        Scene scene = new Scene(root, width, height);
        MoviesApp.stage.setScene(scene);
        MoviesApp.stage.setTitle("MovieLib");
        MoviesApp.stage.show();
    }

    public static void showAlert (String message, AlertType alertType) {

        Alert alert = new Alert(alertType);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();

    }

    public static Optional<Integer> askForReview () {

        Optional<Integer> grade = Optional.empty();
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("MoviesLib");
        dialog.setHeaderText(null);
        dialog.setContentText("Entre com a nota do filme (0-10): ");
        boolean continueToAsk = true;

        while (continueToAsk) {

            try {

                Optional<String> result = dialog.showAndWait();

                if (result.isPresent()) {

                    grade = Optional.of(Integer.valueOf(Integer.parseInt(result.get())));

                }

                continueToAsk = false;

            } catch (NumberFormatException e) {

                MovieViewFX.showAlert("Entrada inválida! Entre com um número entre 0 e 10.", AlertType.WARNING);
                continueToAsk = true;

            } catch (Exception e) {

                MovieViewFX.showAlert("Um erro inesperado ocorreu!", AlertType.ERROR);
                e.printStackTrace();
                continueToAsk = false;

            }

        }

        return grade;

    }

}
