package com.example.task2fabrik.FloaderShape;

import com.example.task2fabrik.Figures.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ShapeFactory {

    private static final Map<Integer, Supplier<Shape>> shapeMap = new HashMap<>();

    static {
        shapeMap.put(0, Circle::new);
        shapeMap.put(1, Straight::new);
        shapeMap.put(2, Angle::new);
        shapeMap.put(3, Triangle::new);
        shapeMap.put(4, Square::new);
        shapeMap.put(5, Pentagon::new);
        shapeMap.put(6, Hexagon::new);
        shapeMap.put(7, Heptagon::new);
        shapeMap.put(8, Octagon::new);
    }

    public Shape createShape(int numberOfSides) {
        Supplier<Shape> supplier = shapeMap.get(numberOfSides);
        return (supplier != null) ? supplier.get() : null;
    }
}

