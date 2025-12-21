package com.example.guardian;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class ShapeWrapper {
    private Shape shape;
    private Momento currentState;

    public ShapeWrapper(Shape shape) {
        this.shape = shape;
        saveState();
    }

    //Создание снимка состояния
    public Momento saveState() {
        currentState = new Momento(
                shape.getStrokeWidth(),
                (Color) shape.getStroke(),
                shape.getLayoutX(),
                shape.getLayoutY()
        );
        return currentState;
    }

    //Восстановление состояния из снимка
    public void restoreState(Momento momento) {
        if (momento != null) {
            shape.setStrokeWidth(momento.getStrokeWidth());
            shape.setStroke(momento.getStrokeColor());
            shape.setLayoutX(momento.getLayoutX());
            shape.setLayoutY(momento.getLayoutY());
        }
    }

    //выделения фигуры
    public void select() {
        shape.setStrokeWidth(3);
        shape.setStroke(Color.RED);
        shape.toFront();
    }

    //Метод перемещения
    public void relocate(double x, double y) {
        shape.setLayoutX(x);
        shape.setLayoutY(y);
    }

    public Shape getShape() { return shape; }
    public double getLayoutX() { return shape.getLayoutX(); }
    public double getLayoutY() { return shape.getLayoutY(); }
}
