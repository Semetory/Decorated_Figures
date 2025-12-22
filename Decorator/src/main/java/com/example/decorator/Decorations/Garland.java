package com.example.decorator.Decorations;

import com.example.decorator.ChristmasTree;
import com.example.decorator.TreeDecorator;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.List;

public class Garland extends TreeDecorator {

    public Garland(ChristmasTree tree) {
        super(tree);
    }

    @Override
    public void draw(Pane pane) {
        super.draw(pane);
        drawGarland(pane);
    }

    private void drawGarland(Pane pane) {
        List<Circle> lights = new ArrayList<>();

        createLightsOnTriangle(lights, pane,
                200, 100,
                140, 180,
                260, 180,
                12);

        createLightsOnTriangle(lights, pane,
                200, 150,
                120, 250,
                280, 250,
                16);

        createLightsOnTriangle(lights, pane,
                200, 200,
                100, 320,
                300, 320,
                20);
    }

    private void createLightsOnTriangle(List<Circle> lights,
                                        Pane pane,
                                        double topX, double topY,
                                        double leftX, double leftY,
                                        double rightX, double rightY,
                                        int numLights) {

        int lightsPerSide = numLights / 3;
        int colorIndex = 0;
        Color[] colors = {Color.RED, Color.YELLOW, Color.BLUE, Color.GREEN};

        for (int i = 0; i < lightsPerSide; i++) {
            double t = (double) i / (lightsPerSide - 1);
            double x = topX + (leftX - topX) * t;
            double y = topY + (leftY - topY) * t;

            addLight(lights, pane, x, y, colors[colorIndex % colors.length]);
            colorIndex++;
        }

        for (int i = 0; i < lightsPerSide; i++) {
            double t = (double) i / (lightsPerSide - 1);
            double x = leftX + (rightX - leftX) * t;
            double y = leftY + (rightY - leftY) * t;

            addLight(lights, pane, x, y, colors[colorIndex % colors.length]);
            colorIndex++;
        }

        for (int i = 0; i < lightsPerSide; i++) {
            double t = (double) i / (lightsPerSide - 1);
            double x = rightX + (topX - rightX) * t;
            double y = rightY + (topY - rightY) * t;

            addLight(lights, pane, x, y, colors[colorIndex % colors.length]);
            colorIndex++;
        }
    }

    private void addLight(List<Circle> lights,
                          Pane pane, double x, double y, Color color) {
        Circle light = new Circle(x, y, 4);
        light.setFill(color);
        light.setStroke(Color.WHITE);
        light.setStrokeWidth(1);

        lights.add(light);
        pane.getChildren().add(light);
    }

    @Override public String decorate() { return super.decorate() + " + Гирлянда"; }
    @Override public float cost() { return super.cost() + 600.0f; }
}