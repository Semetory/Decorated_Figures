package com.example.prototype;

import com.example.prototype.shapes.*;
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

    @FXML private ListView<Shape> listView;
    @FXML private Canvas canvas;
    @FXML private ColorPicker colorPicker;
    @FXML private ColorPicker strokeColorPicker;
    @FXML private Slider strokeSlider;
    @FXML private Label shapeNameLabel;
    @FXML private Button shadowButton;
    @FXML private Slider shadowRadiusSlider;
    @FXML private Slider shadowOffsetXSlider;
    @FXML private Slider shadowOffsetYSlider;
    @FXML private ColorPicker shadowColorPicker;
    @FXML private Button clearButton;
    @FXML private Button effectButton;

    private GraphicsContext gc;
    private boolean shadowEnabled = false;
    private DropShadow shadowEffect;
    private ObservableList<Shape> shapesList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gc = canvas.getGraphicsContext2D();

        shadowEffect = new DropShadow();
        shadowEffect.setColor(Color.GRAY);
        shadowEffect.setRadius(10);
        shadowEffect.setOffsetX(5);
        shadowEffect.setOffsetY(5);

        //Создаем прототипы фигур
        Circle circlePrototype = new Circle();
        circlePrototype.setColor(Color.RED);

        Square squarePrototype = new Square();
        squarePrototype.setColor(Color.BLUE);

        Rectangle rectanglePrototype = new Rectangle();
        rectanglePrototype.setColor(Color.GREEN);

        Triangle trianglePrototype = new Triangle();
        trianglePrototype.setColor(Color.ORANGE);

        //Заполняем список прототипами
        shapesList = FXCollections.observableArrayList(
                circlePrototype,
                squarePrototype,
                rectanglePrototype,
                trianglePrototype
        );

        //Настраиваем ListView
        listView.setItems(shapesList);
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        //Устанавливаем фабрику ячеек для отображения названий фигур
        listView.setCellFactory(lv -> new ListCell<Shape>() {
            @Override
            protected void updateItem(Shape item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.toString());
                }
            }
        });

        //Обработчик выбора фигуры
        listView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        shapeNameLabel.setText("Выбрано: " + newValue.toString());
                    }
                }
        );

        //Настраиваем слайдеры тени
        shadowRadiusSlider.setMin(0);
        shadowRadiusSlider.setMax(30);
        shadowRadiusSlider.setValue(10);

        shadowOffsetXSlider.setMin(-20);
        shadowOffsetXSlider.setMax(20);
        shadowOffsetXSlider.setValue(5);

        shadowOffsetYSlider.setMin(-20);
        shadowOffsetYSlider.setMax(20);
        shadowOffsetYSlider.setValue(5);

        //Обработчики для слайдеров тени
        shadowRadiusSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            shadowEffect.setRadius(newValue.doubleValue());
        });

        shadowOffsetXSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            shadowEffect.setOffsetX(newValue.doubleValue());
        });

        shadowOffsetYSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            shadowEffect.setOffsetY(newValue.doubleValue());
        });

        //Обработчик для цвета тени
        shadowColorPicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            shadowEffect.setColor(newValue);
        });

        //Выбираем первую фигуру по умолчанию
        if (!shapesList.isEmpty()) {
            listView.getSelectionModel().select(0);
        }
    }

    @FXML
    public void drawShape(MouseEvent mouseEvent) {
        int index = listView.getSelectionModel().getSelectedIndex();

        if (index >= 0) {
            //Получаем прототип из списка
            Shape prototype = listView.getSelectionModel().getSelectedItem();

            //Клонируем прототип с исп паттерна Прототип
            Shape clonedShape = (Shape) prototype.clone();

            //Настраиваем клонированную фигуру
            clonedShape.setColor(colorPicker.getValue());
            clonedShape.setStrokeColor(strokeColorPicker.getValue());
            clonedShape.setStrokeWidth(strokeSlider.getValue());
            clonedShape.setXY(mouseEvent.getX(), mouseEvent.getY());

            //Сохраняем состояние GraphicsContext
            gc.save();

            //Если тень включена, применяем эффект
            if (shadowEnabled) {
                //эффект тени
                DropShadow shadow = new DropShadow();
                shadow.setColor(shadowColorPicker.getValue());
                shadow.setRadius(shadowRadiusSlider.getValue());
                shadow.setOffsetX(shadowOffsetXSlider.getValue());
                shadow.setOffsetY(shadowOffsetYSlider.getValue());

                //Применяем эффект для GraphicsContext
                gc.setEffect(shadow);
            }

            //вывод основную клонированную фигуру
            clonedShape.draw(gc, clonedShape.getX(), clonedShape.getY());

            //убираем эффект
            gc.setEffect(null);

            //восстанавл состояние GraphicsContext
            gc.restore();

            //Обновляем информацию о фигуре
            shapeNameLabel.setText("Нарисован: " + clonedShape.toString());
        } else { shapeNameLabel.setText("Выберите фигуру из списка!"); }
    }

    @FXML
    public void clearCanvas() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        shapeNameLabel.setText("Холст очищен");
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
            Shape shape = listView.getSelectionModel().getSelectedItem();

            if (shape instanceof Circle) {
                ((Circle) shape).setRadius(40);
                shapeNameLabel.setText("Эффект: увеличен размер круга");
            } else if (shape instanceof Square) {
                ((Square) shape).setSide(50);
                shapeNameLabel.setText("Эффект: увеличен размер квадрата");
            } else if (shape instanceof Triangle) {
                ((Triangle) shape).setSide(80);
                shapeNameLabel.setText("Эффект: увеличен размер триугольника");
            } else if (shape instanceof Rectangle) {
                ((Rectangle) shape).setDimensions(100, 100);
                shapeNameLabel.setText("Эффект: увеличен размер приямоугольника");
            }
            listView.refresh();
        }
    }
}
