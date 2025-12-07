package com.example.drawinfigures.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class RoundedRectangle extends Shape {

    public double width;
    public double height;
    public double arcW;
    public double arcH;

    public RoundedRectangle(Color fill, Color stroke,
                            double x, double y,
                            double width, double height,
                            double arcW, double arcH,
                            double strokeWidth) {

        super(fill, stroke, x, y, strokeWidth);
        this.width = width;
        this.height = height;
        this.arcW = arcW;
        this.arcH = arcH;
    }

    @Override
    public void draw(GraphicsContext gc) {

        gc.setFill(fillColor);
        gc.setStroke(strokeColor);
        gc.setLineWidth(strokeWidth);

        gc.fillRoundRect(x, y, width, height, arcW, arcH);
        gc.strokeRoundRect(x, y, width, height, arcW, arcH);
    }
}
