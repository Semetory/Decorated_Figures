package com.example.guardian;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class Momento {
    private Shape shape;
    private double strokeWidth;
    private Color strokeColor;
    private double layoutX;
    private double layoutY;

    public Momento(Shape shape) {
        this.shape = shape;
        this.strokeWidth = shape.getStrokeWidth();
        this.strokeColor = (Color) shape.getStroke();
        this.layoutX = shape.getLayoutX();
        this.layoutY = shape.getLayoutY();
    }

    public Shape getState() {
        shape.setStrokeWidth(strokeWidth);
        shape.setStroke(strokeColor);
        shape.relocate(layoutX, layoutY);
        return shape;
    }

    public Shape initState() {
        shape.setStrokeWidth(3);
        shape.setStroke(Color.RED);
        return shape;
    }

    public void relocate(double x, double y) {
        shape.relocate(x, y);
    }

    public Shape getShape() {
        return shape;
    }

    public double getStrokeWidth() {
        return strokeWidth;
    }

    public Color getStrokeColor() {
        return strokeColor;
    }

}
