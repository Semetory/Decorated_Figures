package com.example.guardian;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Text;

import java.util.*;

public class HelloController {

    @FXML private Pane drawingPane;
    @FXML private Pane toolbar;
    @FXML private Pane statusBar;

    private List<Momento> selectedShapes = new ArrayList<>();
    private MemoSelect caretaker = new MemoSelect();
    private Map<Shape, double[]> dragOffsets = new HashMap<>();
    private Text statusText = new Text();
    private List<Shape> shapes = new ArrayList<>();
    private boolean dragging = false;

    @FXML
    public void initialize() {
        // Создаем фигуры
        Rectangle rectangle1 = new Rectangle(20, 50, Color.LIGHTGRAY);
        rectangle1.setLayoutX(200); rectangle1.setLayoutY(300);

        Rectangle rectangle2 = new Rectangle(100, 50, Color.WHITE);
        rectangle2.setStroke(Color.BLACK); rectangle2.setArcWidth(10);
        rectangle2.setLayoutX(300); rectangle2.setLayoutY(200);

        Circle circle1 = new Circle(40, Color.LIGHTGRAY);
        circle1.setLayoutX(50); circle1.setLayoutY(50);
        circle1.setCenterX(40); circle1.setCenterY(40);

        Circle circle2 = new Circle(40, Color.YELLOW);
        circle2.setStroke(Color.BLACK); circle2.setStrokeWidth(2);
        circle2.setLayoutX(100); circle2.setLayoutY(100);
        circle2.setCenterX(40); circle2.setCenterY(40);

        Polygon triangle = new Polygon(50,0,0,50,100,50);
        triangle.setFill(Color.WHITE); triangle.setStroke(Color.RED);
        triangle.setLayoutX(400); triangle.setLayoutY(100);

        shapes.addAll(Arrays.asList(rectangle1, rectangle2, circle1, circle2, triangle));

        for (Shape shape : shapes) {
            drawingPane.getChildren().add(shape);
            shape.setOnMousePressed(e -> onMousePressed(e, shape));
            shape.setOnMouseDragged(this::onMouseDragged);
            shape.setOnMouseReleased(this::onMouseReleased);
            shape.setOnMouseMoved(this::updateStatus);
        }

        drawingPane.setOnMousePressed(e -> {
            if (e.getTarget() == drawingPane) {
                clearSelection();
            }
        });

        statusText.setX(5); statusText.setY(20);
        statusBar.getChildren().add(statusText);
    }

    private void onMousePressed(MouseEvent e, Shape shape) {
        if (!e.isControlDown() && !selectedShapesContains(shape)) {
            clearSelection();
        }

        if (!selectedShapesContains(shape)) {
            Momento m = new Momento(shape);
            m.initState().toFront();
            selectedShapes.add(m);
            caretaker.push(m);
        }

        highlightSelected();

        dragOffsets.clear();
        for (Momento m : selectedShapes) {
            double offsetX = e.getSceneX() - m.getShape().getLayoutX();
            double offsetY = e.getSceneY() - m.getShape().getLayoutY();
            dragOffsets.put(m.getShape(), new double[]{offsetX, offsetY});
        }
        dragging = true;

        updateStatus(e);
    }

    private void highlightSelected() {
        for (Momento m : shapesToMomentoList()) {
            if (selectedShapesContains(m.getShape())) {
                m.getShape().setStroke(Color.RED);
                m.getShape().setStrokeWidth(3);
            } else {
                m.getState();
            }
        }
    }

    private List<Momento> shapesToMomentoList() {
        List<Momento> list = new ArrayList<>();
        for (Shape s : shapes) {
            list.add(new Momento(s));
        }
        return list;
    }

    private boolean selectedShapesContains(Shape shape) {
        return selectedShapes.stream().anyMatch(m -> m.getShape() == shape);
    }

    private void clearSelection() {
        for (Momento m : selectedShapes) {
            Shape s = m.getShape();
            s.setStroke(m.getStrokeColor());
            s.setStrokeWidth(m.getStrokeWidth());
        }
        selectedShapes.clear();
        dragOffsets.clear();
        dragging = false;
    }


    private void onMouseDragged(MouseEvent e) {
        if (!dragging || selectedShapes.isEmpty()) return;

        for (Momento m : selectedShapes) {
            double[] offsets = dragOffsets.get(m.getShape());
            if (offsets == null) continue;

            double newX = e.getSceneX() - offsets[0];
            double newY = e.getSceneY() - offsets[1];
            m.relocate(newX, newY);
        }
        updateStatus(e);
    }

    private void onMouseReleased(MouseEvent e) {
        dragging = false;
        for (Momento m : selectedShapes) {
            caretaker.push(m);
        }
        dragOffsets.clear();
        updateStatus(e);
    }

    private void updateStatus(MouseEvent e) {
        String targetName = e.getTarget().getClass().getSimpleName();
        double x = e.getSceneX();
        double y = e.getSceneY();
        statusText.setText("Target: " + targetName + " | X: " + String.format("%.1f", x) + " Y: " + String.format("%.1f", y));
    }

}
