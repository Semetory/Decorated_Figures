package com.example.decorator;

import javafx.scene.layout.Pane;

public abstract class TreeDecorator implements ChristmasTree {

    private ChristmasTree tree;

    public TreeDecorator(ChristmasTree tree) { this.tree = tree; }

    @Override public void draw(Pane pane) { tree.draw(pane); }
    @Override public String decorate() { return tree.decorate(); }
    @Override public float cost() { return tree.cost(); }
}