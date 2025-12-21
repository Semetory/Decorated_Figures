package com.example.drawinfigures.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle extends Shape {

    public double radius;

    public Circle(Color fill, Color stroke,
                  double x, double y, double radius, double strokeWidth) {

        super(fill, stroke, x, y, strokeWidth);

        this.x = x;
        this.y = y;
        this.radius = radius;
        this.strokeWidth = strokeWidth;
    }

    @Override
    public void draw(GraphicsContext gc) {

        gc.setFill(fillColor);
        gc.setStroke(strokeColor);
        gc.setLineWidth(strokeWidth);

        gc.fillOval(x, y, radius * 2, radius * 2);
        gc.strokeOval(x, y, radius * 2, radius * 2);
    }
}
