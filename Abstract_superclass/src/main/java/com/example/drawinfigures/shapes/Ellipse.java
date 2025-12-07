package com.example.drawinfigures.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Ellipse extends Shape {
    public double radiusX;
    public double radiusY;

    public Ellipse(Color fill, Color stroke, double x, double y, double radiusX, double radiusY, double strokeWidth) {
        super(fill, stroke, x, y, strokeWidth);
        this.radiusX = radiusX;
        this.radiusY = radiusY;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(fillColor);
        gc.setStroke(strokeColor);
        gc.setLineWidth(strokeWidth);
        gc.fillOval(x, y, radiusX, radiusY);
        gc.strokeOval(x, y, radiusX, radiusY);
    }
}
