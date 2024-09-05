package com.example.strategypattern;

import javafx.beans.property.StringProperty;

public class Context {

    private static DiscountStrategy strategy;

    public static void setStrategy(DiscountStrategy s){
        strategy = s;
    }

    public static StringProperty executeStrategy(){
        return strategy.makeDiscount();
    }

}
