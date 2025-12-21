package com.example.drawinfigures.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rectangle extends Shape {

    public double width;
    public double height;

    public Rectangle(Color fill, Color stroke,
                     double x, double y,
                     double width, double height,
                     double strokeWidth) {

        super(fill, stroke, x, y, strokeWidth);

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.strokeWidth = strokeWidth;
    }

    @Override
    public void draw(GraphicsContext gc) {

        gc.setFill(fillColor);
        gc.setStroke(strokeColor);
        gc.setLineWidth(strokeWidth);

        gc.fillRect(x, y, width, height);
        gc.strokeRect(x, y, width, height);
    }
}
