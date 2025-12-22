package com.example.decorator.Decorations;

import com.example.decorator.ChristmasTree;
import com.example.decorator.TreeDecorator;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.scene.effect.Glow;
import javafx.scene.effect.DropShadow;

public class Star extends TreeDecorator {
    private Color starColor;

    public Star(ChristmasTree tree) {
        super(tree);
        this.starColor = Color.GOLD;
    }

    public Star(ChristmasTree tree, Color color) {
        super(tree);
        this.starColor = color;
    }

    @Override
    public void draw(Pane pane) {
        super.draw(pane);
        drawStar(pane);
    }

    private void drawStar(Pane pane) {

        double centerX = 200;
        double centerY = 95;

        Polygon star = createFivePointedStar(centerX, centerY, 25, 12);

        star.setFill(starColor);
        star.setStroke(Color.ORANGE);
        star.setStrokeWidth(2);

        Glow glow = new Glow(0.6);
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.YELLOW.deriveColor(0, 1, 1, 0.3));
        shadow.setRadius(10);
        shadow.setSpread(0.3);
        glow.setInput(shadow);
        star.setEffect(glow);

        pane.getChildren().add(star);
    }

    private Polygon createFivePointedStar(double centerX, double centerY,
                                          double outerRadius, double innerRadius) {
        Polygon star = new Polygon();

        double startAngle = -Math.PI / 2;

        for (int i = 0; i < 10; i++) {
            double angle = startAngle + i * Math.PI / 5;
            double radius = (i % 2 == 0) ? outerRadius : innerRadius;

            double x = centerX + radius * Math.cos(angle);
            double y = centerY + radius * Math.sin(angle);

            star.getPoints().addAll(x, y);
        }
        return star;
    }

    @Override public String decorate() { return super.decorate() + " + Звезда"; }
    @Override public float cost() { return super.cost() + 450.0f; }
}