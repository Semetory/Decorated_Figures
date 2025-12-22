package com.example.decorator;

import javafx.scene.layout.Pane;

public interface ChristmasTree {
    void draw(Pane pane);
    String decorate();
    float cost();
}
