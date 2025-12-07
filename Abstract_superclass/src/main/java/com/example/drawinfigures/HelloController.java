package com.example.drawinfigures;

import com.example.drawinfigures.shapes.*;
import com.example.drawinfigures.utils.PngWriter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HelloController {

    @FXML private Canvas canvas;
    @FXML private Button b_circle, b_ellipse, b_rectangle, b_rounded_rectangle, b_square,saveButton;
    @FXML private ColorPicker cPicker;
    @FXML private Slider thicknessSlider;
    @FXML private Button fill;

    private final List<Shape> shapes = new ArrayList<>();
    private DrawMode mode = DrawMode.NONE;
    private boolean fillMode = true;
    private double startX, startY, endX, endY;
    private boolean isDrawing = false;

    private enum DrawMode {
        NONE, PEN, ERASE, RECTANGLE, SQUARE, CIRCLE, ELLIPSE, ROUNDED_RECT
    }

    @FXML
    public void initialize() {
        cPicker.setValue(Color.BLACK);
        thicknessSlider.setValue(2);

        canvas.setOnMousePressed(this::onMousePressed);
        canvas.setOnMouseDragged(this::onMouseDragged);
        canvas.setOnMouseReleased(this::onMouseReleased);
    }

    private GraphicsContext gc() {
        return canvas.getGraphicsContext2D();
    }

    private void onMousePressed(MouseEvent e) {
        startX = e.getX();
        startY = e.getY();
        isDrawing = true;

        if (mode == DrawMode.PEN || mode == DrawMode.ERASE) {
            gc().setLineWidth(thicknessSlider.getValue());
            gc().setStroke(mode == DrawMode.ERASE ? Color.WHITE : cPicker.getValue());
            gc().beginPath();
            gc().moveTo(startX, startY);
            gc().stroke();
        }
    }

    private void onMouseDragged(MouseEvent e) {
        if (!isDrawing) return;
        endX = e.getX();
        endY = e.getY();

        GraphicsContext g = gc();

        switch (mode) {
            case PEN -> {
                g.lineTo(endX, endY);
                g.stroke();
            }
            case ERASE -> {
                g.setStroke(Color.WHITE);
                g.setLineWidth(thicknessSlider.getValue());
                g.lineTo(endX, endY);
                g.stroke();
            }
            default -> redrawPreview();
        }
    }

    private void onMouseReleased(MouseEvent e) {
        if (!isDrawing) return;
        isDrawing = false;
        endX = e.getX();
        endY = e.getY();

        Shape s = switch (mode) {
            case RECTANGLE -> new Rectangle(
                    fillMode ? cPicker.getValue() : Color.TRANSPARENT,
                    cPicker.getValue(),
                    Math.min(startX, endX), Math.min(startY, endY),
                    Math.abs(endX - startX), Math.abs(endY - startY),
                    thicknessSlider.getValue()
            );
            case SQUARE -> {
                double side = Math.min(Math.abs(endX - startX), Math.abs(endY - startY));
                yield new Square(
                        fillMode ? cPicker.getValue() : Color.TRANSPARENT,
                        cPicker.getValue(),
                        startX, startY, side, thicknessSlider.getValue()
                );
            }
            case CIRCLE -> {
                double radius = Math.hypot(endX - startX, endY - startY);
                yield new Circle(
                        fillMode ? cPicker.getValue() : Color.TRANSPARENT,
                        cPicker.getValue(),
                        startX - radius, startY - radius,
                        radius, thicknessSlider.getValue()
                );
            }
            case ELLIPSE -> new Ellipse(
                    fillMode ? cPicker.getValue() : Color.TRANSPARENT,
                    cPicker.getValue(),
                    Math.min(startX, endX), Math.min(startY, endY),
                    Math.abs(endX - startX), Math.abs(endY - startY),
                    thicknessSlider.getValue()
            );
            case ROUNDED_RECT -> new RoundedRectangle(
                    fillMode ? cPicker.getValue() : Color.TRANSPARENT,
                    cPicker.getValue(),
                    Math.min(startX, endX), Math.min(startY, endY),
                    Math.abs(endX - startX), Math.abs(endY - startY),
                    20, 20, thicknessSlider.getValue()
            );
            default -> null;
        };

        if (s != null) {
            shapes.add(s);
            redraw();
        }
    }

    private void redraw() {
        gc().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (Shape s : shapes) {
            s.draw(gc());
        }
    }

    private void redrawPreview() {
        redraw();
        GraphicsContext g = gc();
        g.setStroke(Color.GRAY);
        g.setLineWidth(1);

        switch (mode) {
            case RECTANGLE -> g.strokeRect(
                    Math.min(startX, endX), Math.min(startY, endY),
                    Math.abs(endX - startX), Math.abs(endY - startY)
            );
            case SQUARE -> {
                double side = Math.min(Math.abs(endX - startX), Math.abs(endY - startY));
                g.strokeRect(startX, startY, side, side);
            }
            case CIRCLE -> {
                double radius = Math.hypot(endX - startX, endY - startY);
                g.strokeOval(startX - radius, startY - radius, radius * 2, radius * 2);
            }
            case ELLIPSE -> g.strokeOval(
                    Math.min(startX, endX), Math.min(startY, endY),
                    Math.abs(endX - startX), Math.abs(endY - startY)
            );
            case ROUNDED_RECT -> g.strokeRoundRect(
                    Math.min(startX, endX), Math.min(startY, endY),
                    Math.abs(endX - startX), Math.abs(endY - startY),
                    20, 20
            );
        }
    }

    @FXML private void onPen(ActionEvent e) { mode = DrawMode.PEN; }
    @FXML private void onEraser(ActionEvent e) { mode = DrawMode.ERASE; }
    @FXML private void onRectangleClick(ActionEvent e) { mode = DrawMode.RECTANGLE; }
    @FXML private void onCSquareClick(ActionEvent e) { mode = DrawMode.SQUARE; }
    @FXML private void onCircleClick(ActionEvent e) { mode = DrawMode.CIRCLE; }
    @FXML private void onEllipseClick(ActionEvent e) { mode = DrawMode.ELLIPSE; }
    @FXML private void onRoundedRectangleClick(ActionEvent e) { mode = DrawMode.ROUNDED_RECT; }

    @FXML private void onFillMode(ActionEvent e) {
        fillMode = !fillMode;
        fill.setText(fillMode ? "Заливка: Вкл" : "Заливка: Выкл");
    }

    @FXML
    private void saveCanvas(ActionEvent actionEvent) {
        WritableImage image = canvas.snapshot(null, null);

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Сохранить PNG");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        File file = fileChooser.showSaveDialog(canvas.getScene().getWindow());

        if (file == null) return;

        try {
            savePngWithoutSwing(image, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void savePngWithoutSwing(WritableImage img, File file) throws IOException {
        PixelReader reader = img.getPixelReader();
        int width = (int) img.getWidth();
        int height = (int) img.getHeight();

        // Буфер для RGBA-пикселей
        byte[] buffer = new byte[width * height * 4];
        int index = 0;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int argb = reader.getArgb(x, y);

                // PNG ожидает порядок RGBA, а не ARGB
                buffer[index++] = (byte) ((argb >> 16) & 0xFF); // R
                buffer[index++] = (byte) ((argb >> 8) & 0xFF);  // G
                buffer[index++] = (byte) (argb & 0xFF);         // B
                buffer[index++] = (byte) ((argb >> 24) & 0xFF); // A
            }
        }

        try (FileOutputStream fos = new FileOutputStream(file)) {
            PngWriter.write(fos, width, height, buffer);
        }
    }

}
