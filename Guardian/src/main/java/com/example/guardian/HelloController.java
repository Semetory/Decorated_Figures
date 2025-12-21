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

    private List<ShapeWrapper> shapeWrappers = new ArrayList<>();
    private MemoSelect caretaker = new MemoSelect();
    private Map<ShapeWrapper, double[]> dragOffsets = new HashMap<>();
    private Text statusText = new Text();
    private boolean dragging = false;

    //границы холста
    private double CANVAS_MIN_X = 0;
    private double CANVAS_MIN_Y = 0;
    private double CANVAS_MAX_X = 800;
    private double CANVAS_MAX_Y = 600;

    @FXML
    public void initialize() {
        //размеры холста
        CANVAS_MAX_X = drawingPane.getPrefWidth() > 0 ? drawingPane.getPrefWidth() : 800;
        CANVAS_MAX_Y = drawingPane.getPrefHeight() > 0 ? drawingPane.getPrefHeight() : 600;

        //Первый прямоугольник используем layoutX/layoutY
        Rectangle rectangle1 = new Rectangle(20, 50);
        rectangle1.setFill(Color.LIGHTGRAY);
        rectangle1.setLayoutX(200);
        rectangle1.setLayoutY(300);

        //Второй прямоугольник скругленный
        Rectangle rectangle2 = new Rectangle(100, 50);
        rectangle2.setFill(Color.WHITE);
        rectangle2.setStroke(Color.BLACK);
        rectangle2.setArcWidth(10);
        rectangle2.setArcHeight(10);
        rectangle2.setLayoutX(300);
        rectangle2.setLayoutY(200);

        //Круги
        Circle circle1 = new Circle(40);
        circle1.setFill(Color.LIGHTGRAY);
        circle1.setLayoutX(50);
        circle1.setLayoutY(50);

        Circle circle2 = new Circle(40);
        circle2.setFill(Color.YELLOW);
        circle2.setStroke(Color.BLACK);
        circle2.setStrokeWidth(2);
        circle2.setLayoutX(100);
        circle2.setLayoutY(100);

        //Треугольник
        Polygon triangle = new Polygon(50, 0, 0, 50, 100, 50);
        triangle.setFill(Color.WHITE);
        triangle.setStroke(Color.RED);
        triangle.setLayoutX(400);
        triangle.setLayoutY(100);

        ShapeWrapper[] wrappers = {
                new ShapeWrapper(rectangle1),
                new ShapeWrapper(rectangle2),
                new ShapeWrapper(circle1),
                new ShapeWrapper(circle2),
                new ShapeWrapper(triangle)
        };

        shapeWrappers.addAll(Arrays.asList(wrappers));

        //Добавляем фигуры
        for (ShapeWrapper wrapper : shapeWrappers) {
            Shape shape = wrapper.getShape();
            drawingPane.getChildren().add(shape);

            //Устанавливаем обработчики
            shape.setOnMousePressed(e -> onMousePressed(e, wrapper));
            shape.setOnMouseDragged(this::onMouseDragged);
            shape.setOnMouseReleased(this::onMouseReleased);
            shape.setOnMouseMoved(this::updateStatus);

            //Отладочная информация
            System.out.println("Фигура: " + shape.getClass().getSimpleName() +
                    " layoutX: " + shape.getLayoutX() +
                    " layoutY: " + shape.getLayoutY() +
                    " bounds: " + shape.getBoundsInLocal());
        }

        //Обработчик клика на пустой области
        drawingPane.setOnMousePressed(e -> {
            if (e.getTarget() == drawingPane && !e.isControlDown()) {
                caretaker.clearSelection();
                dragOffsets.clear();
            }
        });

        //проверка изменения размеров панели
        drawingPane.layoutBoundsProperty().addListener((obs, oldBounds, newBounds) -> {
            CANVAS_MAX_X = newBounds.getWidth();
            CANVAS_MAX_Y = newBounds.getHeight();
        });

        statusText.setX(5); statusText.setY(20);
        statusBar.getChildren().add(statusText);

        updateStatusText("Готово. Холст: " + CANVAS_MAX_X + "x" + CANVAS_MAX_Y);
    }

    private void onMousePressed(MouseEvent e, ShapeWrapper wrapper) {
        if (!e.isControlDown()) {
            caretaker.clearSelection();
        }

        if (caretaker.isSelected(wrapper)) {
            //Если фигура выбрана нажали Ctrl - снимаем выделение
            if (e.isControlDown()) {
                caretaker.deselectShape(wrapper);
                return;
            }
        } else {
            //Выделяем фигуру сохраняем состояние
            caretaker.selectShape(wrapper);
            caretaker.push(wrapper);
        }

        dragOffsets.clear();
        for (ShapeWrapper selected : caretaker.getSelectedShapes()) {
            double offsetX = e.getSceneX() - selected.getLayoutX();
            double offsetY = e.getSceneY() - selected.getLayoutY();
            dragOffsets.put(selected, new double[]{offsetX, offsetY});

            //Отладка
            System.out.println("Нажатие на: " + selected.getShape().getClass().getSimpleName() +
                    " sceneX: " + e.getSceneX() + " layoutX: " + selected.getLayoutX() + " offsetX: " + offsetX);
        }

        dragging = true;
        updateStatus(e);
    }

    private void onMouseDragged(MouseEvent e) {
        if (!dragging || caretaker.getSelectedShapes().isEmpty()) return;

        for (ShapeWrapper wrapper : caretaker.getSelectedShapes()) {
            double[] offsets = dragOffsets.get(wrapper);
            if (offsets != null) {
                double newX = e.getSceneX() - offsets[0];
                double newY = e.getSceneY() - offsets[1];

                //Проверяем границы
                double[] constrainedPos = constrainPosition(wrapper.getShape(), newX, newY);
                newX = constrainedPos[0];
                newY = constrainedPos[1];

                wrapper.relocate(newX, newY);

                //Отладка
                if (wrapper.getShape() instanceof Rectangle) {
                    System.out.println("Прямоугольник перемещен: X=" + newX + " Y=" + newY);
                }
            }
        }
        updateStatus(e);
    }

    //метод ограничения позиции
    private double[] constrainPosition(Shape shape, double x, double y) {
        double constrainedX = x;
        double constrainedY = y;

        if (shape instanceof Rectangle) {
            Rectangle rect = (Rectangle) shape;
            double width = rect.getWidth();
            double height = rect.getHeight();

            constrainedX = Math.max(CANVAS_MIN_X, Math.min(x, CANVAS_MAX_X - width));
            constrainedY = Math.max(CANVAS_MIN_Y, Math.min(y, CANVAS_MAX_Y - height));

        } else if (shape instanceof Circle) {
            Circle circle = (Circle) shape;
            double radius = circle.getRadius();

            constrainedX = Math.max(CANVAS_MIN_X + radius, Math.min(x, CANVAS_MAX_X - radius));
            constrainedY = Math.max(CANVAS_MIN_Y + radius, Math.min(y, CANVAS_MAX_Y - radius));

        } else if (shape instanceof Polygon) {
            // Для полигона берем максимальные размеры из его точек
            Polygon poly = (Polygon) shape;
            double maxWidth = 0;
            double maxHeight = 0;

            List<Double> points = poly.getPoints();
            for (int i = 0; i < points.size(); i += 2) {
                maxWidth = Math.max(maxWidth, points.get(i));
                maxHeight = Math.max(maxHeight, points.get(i + 1));
            }

            constrainedX = Math.max(CANVAS_MIN_X, Math.min(x, CANVAS_MAX_X - maxWidth));
            constrainedY = Math.max(CANVAS_MIN_Y, Math.min(y, CANVAS_MAX_Y - maxHeight));
        }

        return new double[]{constrainedX, constrainedY};
    }

    private void onMouseReleased(MouseEvent e) {
        if (dragging) {
            //сохраняем конечное состояние всех перемещенных фигур
            for (ShapeWrapper wrapper : caretaker.getSelectedShapes()) {
                caretaker.push(wrapper);
            }
        }

        dragging = false;
        updateStatus(e);
    }

    private void updateStatus(MouseEvent e) {
        String targetName = e.getTarget().getClass().getSimpleName();
        double x = e.getSceneX();
        double y = e.getSceneY();
        int selectedCount = caretaker.getSelectedShapes().size();

        statusText.setText("Объект: " + targetName +
                " | X: " + String.format("%.1f", x) +
                " Y: " + String.format("%.1f", y) +
                " | Выбрано: " + selectedCount);
    }

    private void updateStatusText(String text) {
        if (statusText != null) {
            statusText.setText(text);
        }
    }

    @FXML
    private void clearSelection() {
        caretaker.clearSelection();
        dragOffsets.clear();
        dragging = false;
        updateStatusText("Выделение снято");
    }
}