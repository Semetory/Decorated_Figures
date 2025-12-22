package com.example.prototype.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle extends Shape {
    private double radius = 30;

    @Override
    public void draw(GraphicsContext gc, double x, double y) {
        this.x = x;
        this.y = y;

        gc.setFill(color);
        gc.fillOval(x - radius, y - radius, radius * 2, radius * 2);

        gc.setStroke(strokeColor);
        gc.setLineWidth(strokeWidth);
        gc.strokeOval(x - radius, y - radius, radius * 2, radius * 2);
    }

    @Override
    public String toString() { return "Круг"; }

    @Override
    public void setColor(Color color) { this.color = color; }

    @Override
    public void setXY(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getRadius() { return radius; }
    public void setRadius(double radius) { this.radius = radius; }
}