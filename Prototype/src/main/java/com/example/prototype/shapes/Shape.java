package com.example.prototype.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Shape implements Cloneable {
    protected Color color;
    protected double x;
    protected double y;

    // Абстрактные методы
    public abstract void draw(GraphicsContext gc, double x, double y);
    public abstract String toString();
    public abstract void setColor(Color color);
    public abstract void setXY(double x, double y);

    // Реализация метода clone()
    @Override
    public Object clone() {
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }

    // Геттеры
    public Color getColor() {
        return color;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
