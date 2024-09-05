package com.example.strategypattern;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ConcreteStrategyLoyalty implements DiscountStrategy{

    public StringProperty makeDiscount() {

        double originalPrice = Double.parseDouble(MainSceneController.getPrice().get());

        // 5% de Desconto.
        double finalPrice = originalPrice * 0.95;
        finalPrice = Math.round(finalPrice * 100.0) / 100.0;

        StringProperty strp = new SimpleStringProperty();

        strp.set(Double.toString(finalPrice));

        return strp;
    }


}
