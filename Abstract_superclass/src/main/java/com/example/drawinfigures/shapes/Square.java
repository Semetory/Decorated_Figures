package com.example.drawinfigures.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Square extends Shape {

    public double size;

    public Square(Color fill, Color stroke,
                  double x, double y,
                  double size,
                  double strokeWidth) {

        super(fill, stroke, x, y, strokeWidth);
        this.size = size;
    }

    @Override
    public void draw(GraphicsContext gc) {

        gc.setFill(fillColor);
        gc.setStroke(strokeColor);
        gc.setLineWidth(strokeWidth);

        gc.fillRect(x, y, size, size);
        gc.strokeRect(x, y, size, size);
    }
}
