package com.example.strategypattern;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ConcreteStrategyCoupon implements DiscountStrategy{

    public StringProperty makeDiscount() {

        double originalPrice = Double.parseDouble(MainSceneController.getPrice().get());

        // 10% de Desconto.
        double finalPrice = originalPrice * 0.9;
        finalPrice = Math.round(finalPrice * 100.0) / 100.0;

        StringProperty strp = new SimpleStringProperty();

        strp.set(Double.toString(finalPrice));

        return strp;
    }
}
