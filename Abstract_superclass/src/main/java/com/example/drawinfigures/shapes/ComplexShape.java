package com.example.drawinfigures.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.List;

public class ComplexShape extends Shape {

    public final List<PathCommand> commands;

    public ComplexShape(Color fill, Color stroke, double strokeWidth, List<PathCommand> commands) {
        super(fill, stroke, 0, 0, strokeWidth);
        this.commands = commands;
    }

    public interface PathCommand {
        void apply(GraphicsContext gc);
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setStroke(strokeColor);
        gc.setFill(fillColor);
        gc.setLineWidth(strokeWidth);

        gc.beginPath();
        for (PathCommand cmd : commands) {
            cmd.apply(gc); // выполняем команду пути
        }
        gc.fill();
        gc.stroke();
        gc.closePath();
    }
}
