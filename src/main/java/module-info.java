module com.example.orderit {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.annotation;

    opens com.example.orderit.model to com.fasterxml.jackson.databind;

    opens com.example.orderit to javafx.fxml;
    exports com.example.orderit;
}