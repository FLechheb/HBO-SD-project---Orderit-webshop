module com.example.orderit {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires com.fasterxml.jackson.annotation;

    opens com.example.orderit to javafx.fxml;
    exports com.example.orderit;
}