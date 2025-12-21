package com.example.task2fabrik.Figures;

import com.example.task2fabrik.FloaderShape.Shape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Octagon implements Shape {
    @Override
    public void draw(GraphicsContext gr) {
        gr.setStroke(Color.ORANGERED);
        gr.setLineWidth(3);
        double[] xPoints = {150, 221, 250, 221, 150, 79, 50, 79};
        double[] yPoints = {50, 79, 150, 221, 250, 221, 150, 79};
        gr.strokePolygon(xPoints, yPoints, 8);
    }
}
