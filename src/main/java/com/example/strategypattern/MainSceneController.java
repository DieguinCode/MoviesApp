package com.example.strategypattern;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainSceneController {

    private static StringProperty price = new SimpleStringProperty("100.00");

    @FXML
    private ImageView productImage;

    @FXML
    private Label priceLabel;


    @FXML
    public void initialize() {
        Image image = new Image(Objects.requireNonNull(getClass().getResource("/images/camisa.jpg")).toExternalForm());
        productImage.setImage(image);

        priceLabel.textProperty().bind(price);
    }

    @FXML
    protected void applyDiscount() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("discount-scene.fxml")));
        Stage stage = (Stage) productImage.getScene().getWindow();
        Scene s = new Scene(root, 720, 720);
        stage.setScene(s);
    }

    public static StringProperty getPrice() {
        return price;
    }

    public static void setPrice(StringProperty p){
        price = p;
    }
}