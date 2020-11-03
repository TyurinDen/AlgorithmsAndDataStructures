package com.github.tyurinden.ordered_list;

import java.util.ArrayList;
import java.util.Objects;

public class OrderedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private boolean _ascending;

    public OrderedList(final boolean asc) {
        head = null;
        tail = null;
        _ascending = asc;
    }

    public int compare(final T v1, final T v2) {
        if ((int) v1 < (int) v2) {
            return -1;
        } else if ((int) v1 == (int) v2) {
            return 0;
        }
        return 1;
        // -1 если v1 < v2
        // 0 если v1 == v2
        // +1 если v1 > v2
    }

    public void add(final T value) {
        if (head == null) { //список пуст
            final Node<T> node = new Node<>(value);
            this.head = node;
            this.tail = node;
        } else {
            Node<T> node = this.head;
            while (node != null) {
                if (compare(node.getValue(), value) >= 0) { // случай 8 -> 1,3,7,9
                    insertAfter(node.prev, new Node<>(value));
                }
                node = node.next;
            }

        }
        // автоматическая вставка value
        // в нужную позицию
    }

    public Node<T> find(final T val) {
        Node<T> node = this.head;
        while (node != null) {
            if (compare(node.getValue(), val) == 0) {
                return node;
            }
            node = node.next;
        }
        return null;
    }

    public void delete(final T val) {
        // здесь будет ваш код
    }

    public void clear(final boolean asc) {
        _ascending = asc;
        // здесь будет ваш код
    }

    public int count() {
        int size = 0;
        Node<T> node = this.head;
        while (node != null) {
            size++;
            node = node.next;
        }
        return size; // здесь будет ваш код подсчёта количества элементов в списке
    }

    ArrayList<Node<T>> getAll() // выдать все элементы упорядоченного
    // списка в виде стандартного списка
    {
        ArrayList<Node<T>> r = new ArrayList<>();
        Node<T> node = head;
        while (node != null) {
            r.add(node);
            node = node.next;
        }
        return r;
    }

    private void insertAfter(final Node<T> nodeAfter, final Node<T> nodeToInsert) {
        if (nodeToInsert == null) {
            return; // it would be right to throw an exception in this case
        }
        if (count() == 0) { // case list is empty
            addInTail(nodeToInsert);
            return;
        }
        if (nodeAfter == null) {
            nodeToInsert.next = this.head;
            this.head = nodeToInsert;
            return;
        }
        if (nodeAfter == this.tail) {
            addInTail(nodeToInsert);
        } else {
            nodeToInsert.next = nodeAfter.next;
            nodeAfter.next = nodeToInsert;
        }
    }

    private void addInTail(final Node<T> item) {
        if (this.head == null) {
            this.head = item;
        } else {
            this.tail.next = item;
        }
        this.tail = item;
    }

    private static final class Node<T> {
        private final T value;
        private Node<T> next;
        private Node<T> prev;

        private Node(final T _value) {
            value = _value;
            next = null;
            prev = null;
        }

        public T getValue() {
            return value;
        }
    }

}
