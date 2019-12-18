package com.github.tyurinden.queue;

import java.util.LinkedList;

public class Queue<T> {
    private LinkedList<T> queue;
    private int size;

    public Queue() {
        queue = new LinkedList<>();
    }

    public void enqueue(T item) {
        queue.addLast(item);
        size++;
    }

    public T dequeue() {
        if (size > 0) {
            size--;
            return queue.removeFirst();
        }
        return null;
    }

    public int size() {
        return size;
    }

}
