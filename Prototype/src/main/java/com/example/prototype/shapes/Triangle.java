package com.example.prototype.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Triangle extends Shape {
    private double side = 40;

    @Override
    public void draw(GraphicsContext gc, double x, double y) {
        this.x = x;
        this.y = y;

        double[] xPoints = {x, x - side/2, x + side/2};
        double[] yPoints = {y - side/2, y + side/2, y + side/2};

        gc.setFill(color);
        gc.fillPolygon(xPoints, yPoints, 3);

        gc.setStroke(strokeColor);
        gc.setLineWidth(strokeWidth);
        gc.strokePolygon(xPoints, yPoints, 3);
    }

    @Override
    public String toString() { return "Треугольник"; }

    @Override
    public void setColor(Color color) { this.color = color; }

    @Override
    public void setXY(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getSide() { return side; }
    public void setSide(double side) { this.side = side; }
}