module com.example.task2fabrik {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.task2fabrik to javafx.fxml;
    exports com.example.task2fabrik;
    exports com.example.task2fabrik.Figures;
    opens com.example.task2fabrik.Figures to javafx.fxml;
    exports com.example.task2fabrik.FloaderShape;
    opens com.example.task2fabrik.FloaderShape to javafx.fxml;
}