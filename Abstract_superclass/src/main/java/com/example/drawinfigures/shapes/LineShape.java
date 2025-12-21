package com.example.drawinfigures.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class LineShape  extends Shape{

    private double x2;
    private double y2;

    public LineShape(Color color, double x1, double y1, double x2, double y2, double width) {
        super(Color.TRANSPARENT, color, x1, y1, width);
        this.x2 = x2;
        this.y2 = y2;
    }

    public double getX2() { return x2; }
    public double getY2() { return y2; }
    public void setX2(double x2) { this.x2 = x2; }
    public void setY2(double y2) { this.y2 = y2; }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setStroke(strokeColor);
        gc.setLineWidth(strokeWidth);
        gc.strokeLine(x, y, x2, y2);
    }

}
