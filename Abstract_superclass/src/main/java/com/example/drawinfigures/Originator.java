package com.example.drawinfigures;

import com.example.drawinfigures.shapes.Shape;

import java.util.ArrayList;
import java.util.List;

public class Originator {

    private List<Shape> shapes = new ArrayList<>();

    public void setShapes(List<Shape> shapes) {
        this.shapes = shapes;
    }

    public Memento save() {
        List<Shape> copy = new ArrayList<>(shapes);
        return new Memento(copy);
    }

    public List<Shape> restore(Memento m) {
        return new ArrayList<>(m.getState());
    }
}
