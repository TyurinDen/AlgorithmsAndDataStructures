package com.github.tyurinden.deque;

import java.util.Objects;

public class Deque<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public Deque() {
        this.head = null;
        this.tail = null;
    }

    public void addFront(final T item) {
        final Node<T> node = new Node<>(item);
        if (this.head == null) { // if (size == 0)
            this.tail = node;
        } else {
            this.head.next = node;
            node.prev = this.head;
        }
        this.head = node;
        size++;
    }

    public void addTail(final T item) {
        final Node<T> node = new Node<>(item);
        if (tail == null) { // if (size == 0)
            this.head = node;
        } else {
            this.tail.prev = node;
            node.next = this.tail;
        }
        this.tail = node;
        size++;
    }

    public T removeFront() {
        if (size == 0) {
            return null;
        }
        if (size == 1) {
            final Node<T> node = this.head;
            this.head = null;
            this.tail = null;
            size--;
            return node.value;
        }
        final Node<T> node = this.head;
        this.head = node.prev;
        this.head.next = null;
        size--;
        return node.value;
    }

    public T removeTail() {
        if (size == 0) {
            return null;
        }
        if (size == 1) {
            final Node<T> node = this.tail;
            this.head = null;
            this.tail = null;
            size--;
            return node.value;
        }
        final Node<T> node = this.tail;
        this.tail = node.next;
        this.tail.prev = null;
        size--;
        return node.value;
    }

    public int size() {
        return size;
    }

    private static final class Node<V> {
        private final V value;
        private Node<V> next;
        private Node<V> prev;

        private Node(final V value) {
            this.value = value;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            final Node<?> node = (Node<?>) o;
            return Objects.equals(value, node.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }

}
