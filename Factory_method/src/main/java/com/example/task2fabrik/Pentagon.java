package com.example.task2fabrik;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Pentagon implements Shape {
    @Override
    public void draw(GraphicsContext gr) {
        gr.setStroke(Color.PURPLE);
        gr.setLineWidth(3);
        double[] xPoints = {150, 250, 210, 90, 50};
        double[] yPoints = {50, 120, 250, 250, 120};
        gr.strokePolygon(xPoints, yPoints, 5);
    }
}

