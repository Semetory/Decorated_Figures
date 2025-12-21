package com.example.guardian;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoSelect {
    //история для каждой фигуры отдельно
    private Map<ShapeWrapper, Deque<Momento>> history = new HashMap<>();

    //выбранные фигуры
    private List<ShapeWrapper> selectedShapes = new ArrayList<>();

    //Сохранение состояние для фигуры
    public void push(ShapeWrapper shape) {
        Momento state = shape.saveState();
        history.computeIfAbsent(shape, k -> new ArrayDeque<>()).push(state);
    }

    //Восст пред состояние для фигуры
    public void pop(ShapeWrapper shape) {
        Deque<Momento> stack = history.get(shape);
        if (stack != null && !stack.isEmpty()) {
            Momento lastState = stack.pop();
            shape.restoreState(lastState);
        }
    }

    //выбранные
    public void selectShape(ShapeWrapper shape) {
        if (!selectedShapes.contains(shape)) {
            selectedShapes.add(shape);
            shape.select();
        }
    }

    //Убрать фигуру из выбранных
    public void deselectShape(ShapeWrapper shape) {
        if (selectedShapes.contains(shape)) {
            selectedShapes.remove(shape);
            pop(shape); // Восстанавливаем предыдущее состояние
        }
    }

    //Очистить выделение
    public void clearSelection() {
        for (ShapeWrapper shape : selectedShapes) {
            pop(shape);
        }
        selectedShapes.clear();
    }

    //Получить выбранные фигуры
    public List<ShapeWrapper> getSelectedShapes() {
        return new ArrayList<>(selectedShapes);
    }

    //Проверить выбрана ли фигура
    public boolean isSelected(ShapeWrapper shape) {
        return selectedShapes.contains(shape);
    }
}
