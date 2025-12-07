package com.example.drawinfigures.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Shape {

    public Color fillColor;
    public Color strokeColor;
    public double x, y;
    public double strokeWidth;

    public Shape(Color fillColor, Color strokeColor,
                 double x, double y, double strokeWidth) {

        this.fillColor = fillColor;
        this.strokeColor = strokeColor;
        this.x = x;
        this.y = y;
        this.strokeWidth = strokeWidth;
    }

    public abstract void draw(GraphicsContext gc);
}
