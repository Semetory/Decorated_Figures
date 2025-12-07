package com.example.task2fabrik;

public class ShapeFactory {
    public Shape createShape(int numberOfSides) {
        switch (numberOfSides) {
            case 0: return new Circle();
            case 1: return new Straight();
            case 2: return new Angle();
            case 3: return new Triangle();
            case 4: return new Square();
            case 5: return new Pentagon();
            default: return null;
        }
    }
}

