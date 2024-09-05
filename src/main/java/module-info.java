module com.example.strategypattern {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.strategypattern to javafx.fxml;
    exports com.example.strategypattern;
}