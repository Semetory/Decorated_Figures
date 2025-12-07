package com.example.task2fabrik;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Square implements Shape {
    @Override
    public void draw(GraphicsContext gr) {
        gr.setStroke(Color.ORANGE);
        gr.setLineWidth(3);
        gr.strokeRect(50, 50, 200, 200); // квадрат 200x200
    }
}

