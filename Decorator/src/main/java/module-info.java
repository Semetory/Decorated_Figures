module com.example.decorator {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.decorator to javafx.fxml;
    exports com.example.decorator;
    exports com.example.decorator.Decorations;
    opens com.example.decorator.Decorations to javafx.fxml;
}