package com.example.drawinfigures.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Polygon extends Shape{

    private double[] xPoints;
    private double[] yPoints;
    private int nPoints;

    public Polygon(Color fillColor, Color strokeColor, double[] xPoints, double[] yPoints, int nPoints, double strokeWidth) {
        //x и y передаем координаты первой точки полигона
        super(fillColor, strokeColor, xPoints.length > 0 ? xPoints[0] : 0, yPoints.length > 0 ? yPoints[0] : 0, strokeWidth);
        this.xPoints = xPoints;
        this.yPoints = yPoints;
        this.nPoints = nPoints;
    }

    public double[] getXPoints() { return xPoints; }
    public double[] getYPoints() { return yPoints; }
    public int getNPoints() { return nPoints; }

    public void setXPoints(double[] xPoints) { this.xPoints = xPoints; }
    public void setYPoints(double[] yPoints) { this.yPoints = yPoints; }
    public void setNPoints(int nPoints) { this.nPoints = nPoints; }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setStroke(strokeColor);
        gc.setFill(fillColor);
        gc.setLineWidth(strokeWidth);

        if (fillColor != Color.TRANSPARENT) {
            gc.fillPolygon(xPoints, yPoints, nPoints);
        }
        gc.strokePolygon(xPoints, yPoints, nPoints);
    }

}
