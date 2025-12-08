package com.example.prototype;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private ListView<String> listView;

    @FXML
    private Canvas canvas;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private ColorPicker strokeColorPicker;

    @FXML
    private Slider strokeSlider;

    @FXML
    private Label shapeNameLabel;

    @FXML
    private Button shadowButton;

    @FXML
    private Slider shadowRadiusSlider;

    @FXML
    private Slider shadowOffsetXSlider;

    @FXML
    private Slider shadowOffsetYSlider;

    @FXML
    private ColorPicker shadowColorPicker;

    private GraphicsContext gc;
    private boolean shadowEnabled = false;
    private DropShadow shadowEffect;

    // Список доступных фигур
    private final String[] shapes = {"Круг", "Квадрат", "Прямоугольник", "Треугольник"};

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Инициализация графического контекста
        gc = canvas.getGraphicsContext2D();

        // Инициализация эффекта тени
        shadowEffect = new DropShadow();
        shadowEffect.setColor(Color.GRAY);
        shadowEffect.setRadius(10);
        shadowEffect.setOffsetX(5);
        shadowEffect.setOffsetY(5);

        // Заполнение ListView простыми строками
        ObservableList<String> items = FXCollections.observableArrayList(shapes);
        listView.setItems(items);
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        // Установка обработчика выбора
        listView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        shapeNameLabel.setText("Фигура: " + newValue);
                    }
                }
        );

        // Настройка слайдеров тени
        shadowRadiusSlider.setMin(0);
        shadowRadiusSlider.setMax(30);
        shadowRadiusSlider.setValue(10);

        shadowOffsetXSlider.setMin(-20);
        shadowOffsetXSlider.setMax(20);
        shadowOffsetXSlider.setValue(5);

        shadowOffsetYSlider.setMin(-20);
        shadowOffsetYSlider.setMax(20);
        shadowOffsetYSlider.setValue(5);

        // Обработчики для слайдеров тени
        shadowRadiusSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            shadowEffect.setRadius(newValue.doubleValue());
        });

        shadowOffsetXSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            shadowEffect.setOffsetX(newValue.doubleValue());
        });

        shadowOffsetYSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            shadowEffect.setOffsetY(newValue.doubleValue());
        });

        // Обработчик для цвета тени
        shadowColorPicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            shadowEffect.setColor(newValue);
        });
    }

    @FXML
    public void drawShape(MouseEvent mouseEvent) {
        int index = listView.getSelectionModel().getSelectedIndex();

        if (index >= 0) {
            // Получаем выбранную фигуру
            String shapeType = listView.getSelectionModel().getSelectedItem();

            // Настройка цвета заливки
            gc.setFill(colorPicker.getValue());

            // Настройка контура
            gc.setStroke(strokeColorPicker.getValue());
            gc.setLineWidth(strokeSlider.getValue());

            double x = mouseEvent.getX();
            double y = mouseEvent.getY();

            // Рисование выбранной фигуры
            switch (shapeType) {
                case "Круг":
                    drawCircle(x, y);
                    break;
                case "Квадрат":
                    drawSquare(x, y);
                    break;
                case "Прямоугольник":
                    drawRectangle(x, y);
                    break;
                case "Треугольник":
                    drawTriangle(x, y);
                    break;
            }
        }
    }

    private void drawCircle(double x, double y) {
        double radius = 30;

        // Если тень включена, рисуем сначала тень
        if (shadowEnabled) {
            gc.save(); // Сохраняем текущее состояние
            gc.setEffect(shadowEffect);
            gc.setFill(shadowColorPicker.getValue());
            gc.fillOval(x - radius, y - radius, radius * 2, radius * 2);
            gc.restore(); // Восстанавливаем состояние
        }

        // Рисуем основную фигуру
        gc.setFill(colorPicker.getValue());
        gc.fillOval(x - radius, y - radius, radius * 2, radius * 2);
        gc.strokeOval(x - radius, y - radius, radius * 2, radius * 2);
    }

    private void drawSquare(double x, double y) {
        double side = 40;

        if (shadowEnabled) {
            gc.save();
            gc.setEffect(shadowEffect);
            gc.setFill(shadowColorPicker.getValue());
            gc.fillRect(x - side/2, y - side/2, side, side);
            gc.restore();
        }

        gc.setFill(colorPicker.getValue());
        gc.fillRect(x - side/2, y - side/2, side, side);
        gc.strokeRect(x - side/2, y - side/2, side, side);
    }

    private void drawRectangle(double x, double y) {
        double width = 60;
        double height = 40;

        if (shadowEnabled) {
            gc.save();
            gc.setEffect(shadowEffect);
            gc.setFill(shadowColorPicker.getValue());
            gc.fillRect(x - width/2, y - height/2, width, height);
            gc.restore();
        }

        gc.setFill(colorPicker.getValue());
        gc.fillRect(x - width/2, y - height/2, width, height);
        gc.strokeRect(x - width/2, y - height/2, width, height);
    }

    private void drawTriangle(double x, double y) {
        double side = 40;
        double[] xPoints = {x, x - side/2, x + side/2};
        double[] yPoints = {y - side/2, y + side/2, y + side/2};

        if (shadowEnabled) {
            gc.save();
            gc.setEffect(shadowEffect);
            gc.setFill(shadowColorPicker.getValue());
            gc.fillPolygon(xPoints, yPoints, 3);
            gc.restore();
        }

        gc.setFill(colorPicker.getValue());
        gc.fillPolygon(xPoints, yPoints, 3);
        gc.strokePolygon(xPoints, yPoints, 3);
    }

    @FXML
    public void clearCanvas() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    @FXML
    public void toggleShadow() {
        shadowEnabled = !shadowEnabled;

        if (shadowEnabled) {
            shadowButton.setText("Выкл. тень");
            shadowButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
            shapeNameLabel.setText("Тень включена");
        } else {
            shadowButton.setText("Вкл. тень");
            shadowButton.setStyle("");
            shapeNameLabel.setText("Тень выключена");
        }
    }

    @FXML
    public void addEffect() {
        int index = listView.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            String shapeType = listView.getSelectionModel().getSelectedItem();
            shapeNameLabel.setText("Эффект добавлен к: " + shapeType);

            // Здесь можно добавить дополнительную логику для эффектов
        }
    }
}
