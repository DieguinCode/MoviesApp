package com.example.strategypattern;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ConcreteStrategySurprise implements DiscountStrategy {

    public StringProperty makeDiscount() {

        double originalPrice = Double.parseDouble(MainSceneController.getPrice().get());

        //Desconto Aleatório entre 5 e 25%.
        double min = 0.75;
        double max = 0.95;
        double desconto =  min + (Math.random() * (max - min));

        double finalPrice = originalPrice * desconto;
        finalPrice = Math.round(finalPrice * 100.0) / 100.0;

        StringProperty strp = new SimpleStringProperty();

        strp.set(Double.toString(finalPrice));

        return strp;
    }


}
