package com.example.task2fabrik;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class HelloController {

    @FXML
    private Canvas can;

    @FXML
    private TextField value1;

    @FXML
    private void addPicker() {
        GraphicsContext gr = can.getGraphicsContext2D();

        String input = value1.getText();
        if (!checkWithRegExp(input)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Предупреждение");
            alert.setHeaderText(null);
            alert.setContentText("Введено нечисло или число не из диапазона от 0 до 5!");
            alert.showAndWait();
            return;
        }

        int numberOfSides = Integer.parseInt(input);
        ShapeFactory shapeFactory = new ShapeFactory();
        Shape shape = shapeFactory.createShape(numberOfSides);

        gr.clearRect(0, 0, can.getWidth(), can.getHeight());
        if (shape != null) {
            shape.draw(gr);
        }
    }

    private boolean checkWithRegExp(String text) {
        return text.matches("[0-5]");
    }
}
