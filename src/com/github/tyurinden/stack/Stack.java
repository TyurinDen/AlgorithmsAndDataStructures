package com.github.tyurinden.stack;

import java.util.Arrays;
import java.util.Collection;

public class Stack<T> {
    private static final int MIN_STACK_CAPACITY = 16;
    private T[] elements;
    private int size = 0;
    private int capacity;

    private void ensureCapacity() {
        if (capacity < size + 1) {
            capacity = (capacity * 3) / 2;
            elements = Arrays.copyOf(elements, capacity);
        }
    }

    public Stack() {
        this(16);
    }

    @SuppressWarnings("unchecked")
    public Stack(int initialCapacity) {
//        assert capacity > 0 : "Stack size must be greater then zero!";
        if (initialCapacity <= 0) {
            throw new RuntimeException("Stack size must be greater then zero!");
        }
        this.capacity = initialCapacity;
        elements = (T[]) new Object[initialCapacity];
    }

    public T push(T val) {
        ensureCapacity();
        elements[size++] = val;
        return val;
    }

    public T pop() {
        if (size == 0) {
            return null;
        }
        return elements[--size];
    }

    public T peek() {
        if (size == 0) {
            return null;
        }
        return elements[size - 1];
    }

    public int size() {
        return size;
    }

    public int getCapacity() {
        return capacity;
    }

    public void pushAll(Collection<? extends T> collection) {
        for (T t: collection) {
            push(t);
        }
    }

    public void popAll(Collection<? super T> collection) {
        while (size > 0) {
            collection.add(pop());
        }
    }

    public void trimToSize() {
        if (size < MIN_STACK_CAPACITY) {
            elements = Arrays.copyOf(elements, MIN_STACK_CAPACITY);
            capacity = MIN_STACK_CAPACITY;
        } else {
            elements = Arrays.copyOf(elements, size);
            capacity = size;
        }
    }

}
