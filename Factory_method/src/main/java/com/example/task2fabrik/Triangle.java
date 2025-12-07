package com.example.task2fabrik;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Triangle implements Shape {
    @Override
    public void draw(GraphicsContext gr) {
        gr.setStroke(Color.RED);
        gr.setLineWidth(3);
        double[] xPoints = {50, 150, 250};
        double[] yPoints = {250, 50, 250};
        gr.strokePolygon(xPoints, yPoints, 3);
    }
}

