module com.example.finalproject {
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.google.gson;


    opens com.example.finalproject to javafx.fxml;
    opens com.example.finalproject.controllers to javafx.fxml;
    exports com.example.finalproject;
    exports com.example.finalproject.controllers;
    exports com.example.finalproject.gsondatastructures;
}