package com.example.prototype.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rectangle extends Shape {
    private double width = 60;
    private double height = 40;

    @Override
    public void draw(GraphicsContext gc, double x, double y) {
        this.x = x;
        this.y = y;

        gc.setFill(color);
        gc.fillRect(x - width/2, y - height/2, width, height);

        gc.setStroke(strokeColor);
        gc.setLineWidth(strokeWidth);
        gc.strokeRect(x - width/2, y - height/2, width, height);
    }

    @Override
    public String toString() { return "Прямоугольник"; }

    @Override
    public void setColor(Color color) { this.color = color; }

    @Override
    public void setXY(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getWidth() { return width; }
    public double getHeight() { return height; }

    public void setDimensions(double width, double height) {
        this.width = width;
        this.height = height;
    }
}