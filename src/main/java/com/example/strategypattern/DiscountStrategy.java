package com.example.strategypattern;

import javafx.beans.property.StringProperty;

public interface DiscountStrategy {
    StringProperty makeDiscount();
}
