package com.example.drawinfigures;

import com.example.drawinfigures.shapes.*;
import javafx.scene.paint.Color;

public class ShapeSerializer {

    //Сериализация фигуры в строку (для сохранения в *.draw)
    public static String serialize(Shape shape) {
        StringBuilder sb = new StringBuilder();
        if (shape instanceof Rectangle r) {
            sb.append("Rectangle;")
                    .append(r.fillColor).append(";")
                    .append(r.strokeColor).append(";")
                    .append(r.x).append(";").append(r.y).append(";")
                    .append(r.width).append(";").append(r.height).append(";")
                    .append(r.strokeWidth);
        } else if (shape instanceof Square s) {
            sb.append("Square;")
                    .append(s.fillColor).append(";")
                    .append(s.strokeColor).append(";")
                    .append(s.x).append(";").append(s.y).append(";")
                    .append(s.size).append(";")
                    .append(s.strokeWidth);
        } else if (shape instanceof Circle c) {
            sb.append("Circle;")
                    .append(c.fillColor).append(";")
                    .append(c.strokeColor).append(";")
                    .append(c.x).append(";").append(c.y).append(";")
                    .append(c.radius).append(";")
                    .append(c.strokeWidth);
        } else if (shape instanceof Ellipse e) {
            sb.append("Ellipse;")
                    .append(e.fillColor).append(";")
                    .append(e.strokeColor).append(";")
                    .append(e.x).append(";").append(e.y).append(";")
                    .append(e.radiusX).append(";").append(e.radiusY).append(";")
                    .append(e.strokeWidth);
        } else if (shape instanceof RoundedRectangle rr) {
            sb.append("RoundedRectangle;")
                    .append(rr.fillColor).append(";")
                    .append(rr.strokeColor).append(";")
                    .append(rr.x).append(";").append(rr.y).append(";")
                    .append(rr.width).append(";").append(rr.height).append(";")
                    .append(rr.arcW).append(";").append(rr.arcH).append(";")
                    .append(rr.strokeWidth);
        }
        return sb.toString();
    }

    //Десериализация строки обратно в фигуру
    public static Shape deserialize(String data) {
        String[] parts = data.split(";");
        String type = parts[0];
        Color fill = Color.valueOf(parts[1]);
        Color stroke = Color.valueOf(parts[2]);
        double x = Double.parseDouble(parts[3]);
        double y = Double.parseDouble(parts[4]);
        switch (type) {
            case "Rectangle" -> {
                double width = Double.parseDouble(parts[5]);
                double height = Double.parseDouble(parts[6]);
                double strokeWidth = Double.parseDouble(parts[7]);
                return new Rectangle(fill, stroke, x, y, width, height, strokeWidth);
            }
            case "Square" -> {
                double size = Double.parseDouble(parts[5]);
                double strokeWidth = Double.parseDouble(parts[6]);
                return new Square(fill, stroke, x, y, size, strokeWidth);
            }
            case "Circle" -> {
                double radius = Double.parseDouble(parts[5]);
                double strokeWidth = Double.parseDouble(parts[6]);
                return new Circle(fill, stroke, x, y, radius, strokeWidth);
            }
            case "Ellipse" -> {
                double radiusX = Double.parseDouble(parts[5]);
                double radiusY = Double.parseDouble(parts[6]);
                double strokeWidth = Double.parseDouble(parts[7]);
                return new Ellipse(fill, stroke, x, y, radiusX, radiusY, strokeWidth);
            }
            case "RoundedRectangle" -> {
                double width = Double.parseDouble(parts[5]);
                double height = Double.parseDouble(parts[6]);
                double arcW = Double.parseDouble(parts[7]);
                double arcH = Double.parseDouble(parts[8]);
                double strokeWidth = Double.parseDouble(parts[9]);
                return new RoundedRectangle(fill, stroke, x, y, width, height, arcW, arcH, strokeWidth);
            }
            default -> throw new IllegalArgumentException("Unknown shape type: " + type);
        }
    }
}
