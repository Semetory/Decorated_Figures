module com.example.prototype {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.prototype to javafx.fxml;
    exports com.example.prototype;
}