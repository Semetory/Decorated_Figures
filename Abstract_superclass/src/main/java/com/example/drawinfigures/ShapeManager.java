package com.example.drawinfigures;

import com.example.drawinfigures.shapes.Shape;
import javafx.scene.canvas.GraphicsContext;
import java.util.ArrayList;
import java.util.List;

public class ShapeManager {

    private final List<Shape> shapes = new ArrayList<>();

    public void add(Shape s) {
        shapes.add(s);
    }

    public void remove(Shape s) {
        shapes.remove(s);
    }

    public List<Shape> getAll() {
        return shapes;
    }

    public void clear() {
        shapes.clear();
    }

    public void redraw(GraphicsContext gc) {
        for (Shape shape : shapes) {
            shape.draw(gc);
        }
    }
}