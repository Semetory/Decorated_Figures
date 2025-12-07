module com.example.guardian {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.example.guardian to javafx.fxml;
    exports com.example.guardian;
}