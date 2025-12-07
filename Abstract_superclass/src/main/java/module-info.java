module com.example.drawinfigures {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.drawinfigures to javafx.fxml;
    exports com.example.drawinfigures;
    exports com.example.drawinfigures.shapes;
    opens com.example.drawinfigures.shapes to javafx.fxml;
}