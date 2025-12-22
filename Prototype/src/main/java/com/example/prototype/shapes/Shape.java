package com.example.prototype.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Shape implements Cloneable {
    protected Color color;
    protected double x;
    protected double y;
    protected Color strokeColor = Color.BLACK;
    protected double strokeWidth = 1.0;

    public abstract void draw(GraphicsContext gc, double x, double y);
    public abstract String toString();
    public abstract void setColor(Color color);
    public abstract void setXY(double x, double y);

    public void setStrokeColor(Color color) { this.strokeColor = color; }
    public void setStrokeWidth(double width) { this.strokeWidth = width; }

    public Color getStrokeColor() { return strokeColor; }
    public double getStrokeWidth() { return strokeWidth; }

    public Color getColor() { return color; }
    public double getX() { return x; }
    public double getY() { return y; }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}