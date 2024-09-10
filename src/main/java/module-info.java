module com.example.strategypattern {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.google.gson;


    opens com.example.strategypattern to javafx.fxml;
    exports com.example.strategypattern;
}