package com.example.task2fabrik.Figures;

import com.example.task2fabrik.FloaderShape.Shape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Heptagon implements Shape {
    @Override
    public void draw(GraphicsContext gr) {
        gr.setStroke(Color.DARKGREEN);
        gr.setLineWidth(3);
        double[] xPoints = {150, 228, 247, 193, 107, 53, 72};
        double[] yPoints = {50, 72, 148, 225, 225, 148, 72};
        gr.strokePolygon(xPoints, yPoints, 7);
    }
}