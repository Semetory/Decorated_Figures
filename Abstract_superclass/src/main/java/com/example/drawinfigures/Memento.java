package com.example.drawinfigures;

import com.example.drawinfigures.shapes.Shape;

import java.util.List;

public class Memento {

    private final List<Shape> state;

    public Memento(List<Shape> state) {
        this.state = state;
    }

    public List<Shape> getState() {
        return state;
    }
}
