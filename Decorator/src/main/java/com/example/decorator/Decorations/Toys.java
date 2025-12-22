package com.example.decorator.Decorations;

import com.example.decorator.ChristmasTree;
import com.example.decorator.TreeDecorator;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import java.util.Random;

public class Toys extends TreeDecorator {
    private Random random = new Random();

    private final double[][] tiers = {
            //{centerX, centerY, widthAtBottom, height, yOffset}
            {200, 140, 120, 80, 100},
            {200, 200, 160, 100, 150},
            {200, 260, 200, 120, 200}
    };

    public Toys(ChristmasTree tree) { super(tree); }

    @Override
    public void draw(Pane pane) {
        super.draw(pane);
        drawToys(pane);
    }

    private void drawToys(Pane pane) {

        for (int tierIndex = 0; tierIndex < tiers.length; tierIndex++) {
            int toysCount = 4 + tierIndex * 2; // 4, 6, 8 игрушек
            for (int i = 0; i < toysCount; i++) {
                addToyToTier(pane, tierIndex, i, toysCount);
            }
        }
    }

    private void addToyToTier(Pane pane, int tierIndex, int toyIndex, int totalToys) {

        double centerX = tiers[tierIndex][0];
        double centerY = tiers[tierIndex][1];
        double width = tiers[tierIndex][2];
        double height = tiers[tierIndex][3];
        double yOffset = tiers[tierIndex][4];

        //игрушки равномерно по ярусу
        double angle = (2 * Math.PI * toyIndex) / totalToys;
        double radius = (width / 2) * (0.3 + 0.5 * random.nextDouble());

        double x = centerX + radius * Math.cos(angle);
        double distanceFromCenter = Math.abs(x - centerX);
        double maxDistance = width / 2; //расстояние от центра до края
        double y = centerY - height/2 + (distanceFromCenter / maxDistance) * height;

        //Y в пределах треугольника
        double minY = centerY - height/2;
        double maxY = centerY + height/2;
        y = Math.min(maxY, Math.max(minY, y));

        x += (random.nextDouble() - 0.5) * 8;
        y += (random.nextDouble() - 0.5) * 8;

        if (!isPointInTriangle(x, y, tierIndex)) {
            x = centerX + (x - centerX) * 0.9;
            y = centerY + (y - centerY) * 0.9;
        }

        int toyType = random.nextInt(4);
        Color color = getRandomBrightColor();

        switch (toyType) {
            case 0: //Шарик
                Circle ball = new Circle(x, y, 7 + random.nextDouble() * 3);
                ball.setFill(color);
                ball.setStroke(Color.WHITE);
                ball.setStrokeWidth(1.5);
                pane.getChildren().add(ball);
                break;

            case 1: //Колокольчик-треугольник вниз
                double bellSize = 6 + random.nextDouble() * 4;
                Polygon bell = new Polygon();
                bell.getPoints().addAll(
                        x, y - bellSize,
                        x - bellSize, y + bellSize,
                        x + bellSize, y + bellSize
                );
                bell.setFill(color);
                bell.setStroke(Color.GOLD);
                bell.setStrokeWidth(1);
                pane.getChildren().add(bell);
                break;

            case 2: //Прямоугольник
                double rectWidth = 8 + random.nextDouble() * 6;
                double rectHeight = 5 + random.nextDouble() * 4;
                Rectangle box = new Rectangle(x - rectWidth/2, y - rectHeight/2, rectWidth, rectHeight);
                box.setFill(color);
                box.setArcWidth(3);
                box.setArcHeight(3);
                box.setStroke(Color.WHITE);
                box.setStrokeWidth(1);
                pane.getChildren().add(box);
                break;

            case 3: //Ромб
                double diamondSize = 6 + random.nextDouble() * 4;
                Polygon diamond = new Polygon();
                diamond.getPoints().addAll(
                        x, y - diamondSize,
                        x + diamondSize, y,
                        x, y + diamondSize,
                        x - diamondSize, y
                );
                diamond.setFill(color);
                diamond.setStroke(Color.SILVER);
                diamond.setStrokeWidth(1);
                pane.getChildren().add(diamond);
                break;
        }
    }

    private boolean isPointInTriangle(double x, double y, int tierIndex) {
        double centerX = tiers[tierIndex][0];
        double centerY = tiers[tierIndex][1];
        double width = tiers[tierIndex][2];
        double height = tiers[tierIndex][3];

        double topX = centerX;
        double topY = centerY - height/2;

        double leftX = centerX - width/2;
        double leftY = centerY + height/2;

        double rightX = centerX + width/2;
        double rightY = centerY + height/2;

        double denominator = ((leftY - rightY) * (topX - rightX) + (rightX - leftX) * (topY - rightY));
        double a = ((leftY - rightY) * (x - rightX) + (rightX - leftX) * (y - rightY)) / denominator;
        double b = ((rightY - topY) * (x - rightX) + (topX - rightX) * (y - rightY)) / denominator;
        double c = 1 - a - b;

        return a >= 0 && a <= 1 && b >= 0 && b <= 1 && c >= 0 && c <= 1;
    }

    private Color getRandomBrightColor() {
        Color[] brightColors = {
                Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW,
                Color.ORANGE, Color.PURPLE, Color.PINK, Color.CYAN,
                Color.MAGENTA, Color.LIME, Color.AQUA, Color.CORAL
        };
        return brightColors[random.nextInt(brightColors.length)];
    }

    @Override public String decorate() { return super.decorate() + " + Игрушки"; }
    @Override public float cost() { return super.cost() + 120.0f; }
}