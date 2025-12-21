package com.example.drawinfigures.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

public class PolyLine extends Shape{

    private final List<Double> pointsX = new ArrayList<>();
    private final List<Double> pointsY = new ArrayList<>();

    public PolyLine(Color color, double startX, double startY, double width) {
        super(Color.TRANSPARENT, color, startX, startY, width);
        addPoint(startX, startY);
    }

    public void addPoint(double px, double py) {
        pointsX.add(px);
        pointsY.add(py);
    }

    @Override
    public void draw(GraphicsContext gc) {
        if (pointsX.size() < 2) return;

        //List в массивы double[] для метода strokePolyline
        double[] xArr = pointsX.stream().mapToDouble(Double::doubleValue).toArray();
        double[] yArr = pointsY.stream().mapToDouble(Double::doubleValue).toArray();

        gc.setStroke(strokeColor);
        gc.setLineWidth(strokeWidth);
        gc.strokePolyline(xArr, yArr, xArr.length);
    }

}