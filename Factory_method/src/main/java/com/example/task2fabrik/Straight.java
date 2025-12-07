package com.example.task2fabrik;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Straight implements Shape {
    @Override
    public void draw(GraphicsContext gr) {
        gr.setStroke(Color.BLUE);
        gr.setLineWidth(5);
        gr.strokeLine(50, 50, 250, 50); // горизонтальный отрезок
    }
}

