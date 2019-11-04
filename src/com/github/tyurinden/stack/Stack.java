package com.github.tyurinden.stack;

import java.util.LinkedList;

public class Stack<T> {
    public LinkedList<T> stack;

    public Stack() {
        stack = new LinkedList<>();
    }

    public void push(T val) {
        stack.addLast(val);
    }

    public T pop() {
        if (stack.size() == 0) {
            return null;
        }
        return stack.removeLast();
    }

    public T peek() {
        if (stack.size() == 0) {
            return null;
        }
        return stack.getLast();
    }

    public int size() {
        return stack.size();
    }

}
