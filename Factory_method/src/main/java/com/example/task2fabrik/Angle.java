package com.example.task2fabrik;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Angle implements Shape {
    @Override
    public void draw(GraphicsContext gr) {
        gr.setStroke(Color.GRAY);
        gr.setLineWidth(10);
        gr.strokeLine(25, 25, 250, 25); // горизонтальная линия
        gr.strokeLine(30, 25, 30, 250); // вертикальная линия
    }
}

