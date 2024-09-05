package com.example.strategypattern;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class DiscountController {

    @FXML
    public Button applyLoyaltyDiscountButton;
    public Button applyCouponDiscountButton;
    public Button applySurpriseDiscountButton;

    @FXML
    public void applyLoyaltyDiscount() throws IOException {

        Context.setStrategy(new ConcreteStrategyLoyalty());
        StringProperty newPrice = Context.executeStrategy();

        MainSceneController.setPrice(newPrice);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("main-scene.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) applyLoyaltyDiscountButton.getScene().getWindow();
        Scene s = new Scene(root, 720 , 720);
        stage.setScene(s);
        stage.show();
    }

    @FXML
    public void applyCouponDiscount() throws IOException {
        Context.setStrategy(new ConcreteStrategyCoupon());
        StringProperty newPrice = Context.executeStrategy();

        MainSceneController.setPrice(newPrice);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("main-scene.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) applyCouponDiscountButton.getScene().getWindow();
        Scene s = new Scene(root, 720 , 720);
        stage.setScene(s);
        stage.show();
    }

    @FXML
    public void applySurpriseDiscount() throws IOException {
        Context.setStrategy(new ConcreteStrategySurprise());
        StringProperty newPrice = Context.executeStrategy();

        MainSceneController.setPrice(newPrice);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("main-scene.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) applySurpriseDiscountButton.getScene().getWindow();
        Scene s = new Scene(root, 720 , 720);
        stage.setScene(s);
        stage.show();
    }
}