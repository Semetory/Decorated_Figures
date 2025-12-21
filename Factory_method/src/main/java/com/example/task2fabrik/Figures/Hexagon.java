package com.example.task2fabrik.Figures;

import com.example.task2fabrik.FloaderShape.Shape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Hexagon implements Shape {
    @Override
    public void draw(GraphicsContext gr) {
        gr.setStroke(Color.BLUE);
        gr.setLineWidth(3);
        double[] xPoints = {250, 200, 100, 50, 100, 200};
        double[] yPoints = {150, 236, 236, 150, 64, 64};
        gr.strokePolygon(xPoints, yPoints, 6);
    }
}
