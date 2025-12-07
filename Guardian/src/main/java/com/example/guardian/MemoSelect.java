package com.example.guardian;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class MemoSelect {
    private Queue<Momento> mementoList = new ArrayDeque<>();

    public void push(Momento state) {
        mementoList.add(state);
    }

    public Momento poll() {
        return mementoList.poll();
    }

    public List<Momento> getAll() {
        return new ArrayList<>(mementoList);
    }
}

