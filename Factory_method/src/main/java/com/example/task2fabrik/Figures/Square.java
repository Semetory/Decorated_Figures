package com.example.task2fabrik.Figures;

import com.example.task2fabrik.FloaderShape.Shape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Square implements Shape {
    @Override
    public void draw(GraphicsContext gr) {
        gr.setStroke(Color.ORANGE);
        gr.setLineWidth(3);
        gr.strokeRect(50, 50, 200, 200); //квадрат 200 на 200
    }
}

