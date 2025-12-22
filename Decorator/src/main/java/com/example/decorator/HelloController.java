package com.example.decorator;

import com.example.decorator.Decorations.Garland;
import com.example.decorator.Decorations.Star;
import com.example.decorator.Decorations.Toys;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.control.CheckBox;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private Pane paintPane;
    @FXML
    private Label statusLabel;
    @FXML
    private CheckBox garlandCheck, starCheck, toysCheck, presentsCheck;

    private ChristmasTree tree;
    private ChristmasTree decoratedTree;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tree = new ChristmasTreeImpl();
        decoratedTree = tree;
        updateTree();
    }

    private void updateTree() {
        paintPane.getChildren().clear();

        decoratedTree = tree;

        if (garlandCheck.isSelected()) {
            decoratedTree = new Garland(decoratedTree);
        }
        if (starCheck.isSelected()) {
            decoratedTree = new Star(decoratedTree);
        }
        if (toysCheck.isSelected()) {
            decoratedTree = new Toys(decoratedTree);
        }
        if (presentsCheck.isSelected()) {
            decoratedTree = new Presents(decoratedTree);
        }

        decoratedTree.draw(paintPane);
        statusLabel.setText(decoratedTree.decorate() + "\nСтоимость: ₽" + decoratedTree.cost());
    }

    @FXML private void handleCheckboxChange(ActionEvent event) { updateTree(); }

    @FXML
    private void addAllDecorations(ActionEvent event) {
        garlandCheck.setSelected(true);
        starCheck.setSelected(true);
        toysCheck.setSelected(true);
        presentsCheck.setSelected(true);
        updateTree();
    }

    @FXML
    private void removeAllDecorations(ActionEvent event) {
        garlandCheck.setSelected(false);
        starCheck.setSelected(false);
        toysCheck.setSelected(false);
        presentsCheck.setSelected(false);
        updateTree();
    }
}