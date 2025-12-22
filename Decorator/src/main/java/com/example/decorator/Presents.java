package com.example.decorator;

import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Polygon;
import java.util.Random;

public class Presents extends TreeDecorator {

    private Random random = new Random();
    public Presents(ChristmasTree tree) { super(tree); }

    @Override
    public void draw(Pane pane) {
        super.draw(pane);
        drawPresents(pane);
    }

    private void drawPresents(Pane pane) {

        Rectangle present1 = new Rectangle(120, 340, 60, 40);
        present1.setFill(Color.RED);
        present1.setArcWidth(15);
        present1.setArcHeight(15);
        present1.setStroke(Color.DARKRED);
        present1.setStrokeWidth(2);

        Rectangle ribbon1v = new Rectangle(148, 340, 4, 40);
        ribbon1v.setFill(Color.GOLD);
        Rectangle ribbon1h = new Rectangle(120, 358, 60, 4);
        ribbon1h.setFill(Color.GOLD);

        Rectangle present2 = new Rectangle(170, 350, 50, 35);
        present2.setFill(Color.BLUE);
        present2.setArcWidth(12);
        present2.setArcHeight(12);
        present2.setStroke(Color.DARKBLUE);
        present2.setStrokeWidth(2);

        Polygon bow = createBow(195, 367);
        bow.setFill(Color.PINK);

        Rectangle present3 = new Rectangle(240, 345, 40, 30);
        present3.setFill(Color.GREEN);
        present3.setArcWidth(10);
        present3.setArcHeight(10);
        present3.setStroke(Color.DARKGREEN);
        present3.setStrokeWidth(2);

        Rectangle ribbon3 = new Rectangle(258, 345, 4, 30);
        ribbon3.setFill(Color.SILVER);

        pane.getChildren().addAll(present1, ribbon1v, ribbon1h,
                present2, bow, present3, ribbon3);
    }

    private Polygon createBow(double centerX, double centerY) {
        Polygon bow = new Polygon();
        bow.getPoints().addAll(
                centerX, centerY,
                centerX - 8, centerY - 5,
                centerX - 5, centerY - 8,
                centerX - 3, centerY - 3
        );
        bow.getPoints().addAll(
                centerX, centerY,
                centerX + 8, centerY - 5,
                centerX + 5, centerY - 8,
                centerX + 3, centerY - 3
        );
        return bow;
    }

    @Override public String decorate() { return super.decorate() + " + Подарки";}
    @Override public float cost() { return super.cost() + 8.0f; }
}