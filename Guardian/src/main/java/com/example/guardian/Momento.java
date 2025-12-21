package com.example.guardian;

import javafx.scene.paint.Color;

public class Momento {
    private final double strokeWidth;
    private final Color strokeColor;
    private final double layoutX;
    private final double layoutY;

    public Momento(double strokeWidth, Color strokeColor, double layoutX, double layoutY) {
        this.strokeWidth = strokeWidth;
        this.strokeColor = strokeColor;
        this.layoutX = layoutX;
        this.layoutY = layoutY;
    }

    public double getStrokeWidth() { return strokeWidth; }
    public Color getStrokeColor() { return strokeColor; }
    public double getLayoutX() { return layoutX; }
    public double getLayoutY() { return layoutY;}

}