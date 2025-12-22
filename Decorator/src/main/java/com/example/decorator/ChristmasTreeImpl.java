package com.example.decorator;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class ChristmasTreeImpl implements ChristmasTree {

    @Override
    public void draw(Pane pane) {

        pane.getChildren().clear();

        //ствол
        Rectangle trunk = new Rectangle(185, 320, 30, 50);
        trunk.setFill(Color.BROWN);
        trunk.setStroke(Color.SADDLEBROWN);
        trunk.setStrokeWidth(2);

        Polygon tier3 = new Polygon();
        tier3.getPoints().addAll(
                100.0, 320.0,
                200.0, 200.0,
                300.0, 320.0
        );
        tier3.setFill(Color.DARKGREEN);
        tier3.setStroke(Color.FORESTGREEN);
        tier3.setStrokeWidth(3);

        // Рисуем средний ярус
        Polygon tier2 = new Polygon();
        tier2.getPoints().addAll(
                120.0, 250.0,
                200.0, 150.0,
                280.0, 250.0
        );
        tier2.setFill(Color.GREEN);
        tier2.setStroke(Color.LIMEGREEN);
        tier2.setStrokeWidth(3);

        Polygon tier1 = new Polygon();
        tier1.getPoints().addAll(
                140.0, 180.0,
                200.0, 100.0,
                260.0, 180.0
        );
        tier1.setFill(Color.LIMEGREEN);
        tier1.setStroke(Color.PALEGREEN);
        tier1.setStrokeWidth(3);

        pane.getChildren().addAll(trunk, tier3, tier2, tier1);
    }

    @Override public String decorate() { return "Елка"; }
    @Override public float cost() { return 1200.0f; }
}
