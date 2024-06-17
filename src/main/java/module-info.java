module org.example.k2_client {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;

    opens org.example.k2_client to javafx.fxml;
    exports org.example.k2_client;
}