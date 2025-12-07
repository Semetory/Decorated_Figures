module com.example.task2fabrik {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.task2fabrik to javafx.fxml;
    exports com.example.task2fabrik;
}