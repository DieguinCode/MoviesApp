module com.example.finalproject {
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.google.gson;


    opens com.example.finalproject to javafx.fxml;
    exports com.example.finalproject;
}