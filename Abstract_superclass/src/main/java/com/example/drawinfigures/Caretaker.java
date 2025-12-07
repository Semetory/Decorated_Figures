package com.example.drawinfigures;

import java.util.Stack;

public class Caretaker {

    private final Stack<Memento> history = new Stack<>();

    public void push(Memento m) {
        history.push(m);
    }

    public Memento pop() {
        if (!history.isEmpty())
            return history.pop();
        return null;
    }
}
