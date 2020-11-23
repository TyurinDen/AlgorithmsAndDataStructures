package com.github.tyurinden.ordered_list;

import java.util.*;

public class OrderedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private boolean _ascending;
    private int size;

    public OrderedList(final boolean asc) {
        head = null;
        tail = null;
        size = 0;
        _ascending = asc;
    }

    public int compare(final T v1, final T v2) { // mandatory
        if (v1 instanceof Integer & v2 instanceof Integer) {
            final Integer i1 = Integer.valueOf(v1.toString().trim());
            final Integer i2 = Integer.valueOf(v2.toString().trim());
            if (i1.compareTo(i2) < 0) {
                return -1;
            } else if (i1.compareTo(i2) == 0) {
                return 0;
            }
            return 1;
        }

        if (v1 instanceof Long & v2 instanceof Long) {
            final Long l1 = Long.valueOf(v1.toString().trim());
            final Long l2 = Long.valueOf(v2.toString().trim());
            if (l1.compareTo(l2) < 0) {
                return -1;
            } else if (l1.compareTo(l2) == 0) {
                return 0;
            }
            return 1;
        }

        if (v1 instanceof Float & v2 instanceof Float) {
            final Float f1 = Float.valueOf(v1.toString().trim());
            final Float f2 = Float.valueOf(v2.toString().trim());
            if (f1.compareTo(f2) < 0) {
                return -1;
            } else if (f1.compareTo(f2) == 0) {
                return 0;
            }
            return 1;
        }

        if (v1 instanceof Double & v2 instanceof Double) {
            final Double d1 = Double.valueOf(v1.toString().trim());
            final Double d2 = Double.valueOf(v2.toString().trim());
            if (d1.compareTo(d2) < 0) {
                return -1;
            } else if (d1.compareTo(d2) == 0) {
                return 0;
            }
            return 1;
        }

        if (v1 instanceof Short & v2 instanceof Short) {
            final Short s1 = Short.valueOf(v1.toString().trim());
            final Short s2 = Short.valueOf(v2.toString().trim());
            if (s1.compareTo(s2) < 0) {
                return -1;
            } else if (s1.compareTo(s2) == 0) {
                return 0;
            }
            return 1;
        }

        if (v1 instanceof Byte & v2 instanceof Byte) {
            final Byte b1 = Byte.valueOf(v1.toString().trim());
            final Byte b2 = Byte.valueOf(v2.toString().trim());
            if (b1.compareTo(b2) < 0) {
                return -1;
            } else if (b1.compareTo(b2) == 0) {
                return 0;
            }
            return 1;
        }

        if (v1 instanceof String & v2 instanceof String) {
            final String str1 = v1.toString().trim();
            final String str2 = v2.toString().trim();
            if (str1.compareTo(str2) < 0) {
                return -1;
            } else if (str1.compareTo(str2) == 0) {
                return 0;
            }
            return 1;
        }
        throw new IllegalArgumentException("Arguments cannot be compare. Not supported types or the types don't match");
        // -1 если v1 < v2
        // 0 если v1 == v2
        // +1 если v1 > v2
    }

    public void add(final T value) { // mandatory
        if (size == 0) { //список пуст
            final Node<T> node = new Node<>(value);
            this.head = node;
            this.tail = node;
            size++;
            return;
        }

        Node<T> node = this.head;
        if (_ascending) { // список упорядочен по возрастанию
            while (node != null) {
                if (compare(value, node.getValue()) <= 0) { // случай 8 -> 1,3,7,8,9
                    insertAfter(node.prev, new Node<>(value));
                    size++;
                    return;
                }
                node = node.next;
            }
        } else { // список упорядочен по убыванию
            while (node != null) {
                if (compare(value, node.getValue()) >= 0) { // случай 8 -> 11,9,6,4,3
                    insertAfter(node.prev, new Node<>(value));
                    size++;
                    return;
                }
                node = node.next;
            }
        }
        insertAfter(this.tail, new Node<>(value)); // добавляем в хвост в независимости от направления порядка списка
        size++;
    }

    // 5? 1,2,4,5,7,9 // 5? 6,7,9,10,14 // 5? 1,2,4,7,8,10 // 5? 1,2,3,4
    // 5? 14,12,10,10,8,6,5,3,1 // 5? 4,3,2,1,1,1 // 5? 14,12,9,6,3,2,1 // 5? 11,10,8,7,6
    //TODO подумать как оптимизировать поиск используя максимально, минимальное и среднее значение в массиве
    public Node<T> find(final T val) { // mandatory
        Node<T> node = this.head;
        if (_ascending) { // массив упорядочен по возрастанию
            while (node != null) { //TODO можно сократить код, перенеся условие внутрь цикла while
                if (compare(val, node.getValue()) < 0) {
                    return null;
                }
                if (compare(val, node.getValue()) == 0) {
                    return node;
                }
                node = node.next;
            }
        } else { // массив упорядочен по убыванию
            while (node != null) {
                if (compare(val, node.getValue()) > 0) {
                    return null;
                }
                if (compare(val, node.getValue()) == 0) {
                    return node;
                }
                node = node.next;
            }
        }
        return null;
    }

    public void delete(final T val) { // mandatory
        Node<T> node = find(val);
        while (node != null) {
            if (size == 1) {
                clear(true);
                return;
            }
            if (node == this.head) {
                this.head = this.head.next;
                this.head.prev = null;
            } else if (node == this.tail) {
                this.tail = this.tail.prev;
                this.tail.next = null;
            } else {
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }
            size--;
            node = find(val);
        }
    }

    public void clear(final boolean asc) { // mandatory
        _ascending = asc;
        this.head = null;
        this.tail = null;
        size = 0;
    }

    public int count() { // mandatory
        return size;
    }

    // выдать все элементы упорядоченного списка в виде стандартного списка
    ArrayList<Node<T>> getAll() {// mandatory
        final ArrayList<Node<T>> r = new ArrayList<>();
        Node<T> node = head;
        while (node != null) {
            r.add(node);
            node = node.next;
        }
        return r;
    }

    public T getNodeValue(final Node<T> node) {
        if (node == null) {
            throw new IllegalArgumentException("The node should must be not null!");
        }
        return node.value;
    }

    public T removeFront() {
        if (size == 0) {
            return null;
        }
        final Node<T> node = this.head;
        if (size == 1) {
            this.head = null;
            this.tail = null;
            size = 0;
            return node.value;
        }
        this.head = node.next;
        this.head.prev = null;
        size--;
        return node.value;
    }

    private void insertAfter(final Node<T> nodeAfter, final Node<T> nodeToInsert) {
        if (nodeToInsert == null) {
            return; // it would be right to throw an exception in this case
        }
        if (size == 0) { // case list is empty
            this.head = nodeToInsert;
            this.tail = nodeToInsert;
            size++;
            return;
        }
        if (nodeAfter == null) {
            nodeToInsert.next = this.head;
            this.head.prev = nodeToInsert;
            this.head = nodeToInsert;
            return;
        }
        if (nodeAfter == this.tail) {
            this.tail.next = nodeToInsert;
            nodeToInsert.prev = this.tail;
            this.tail = nodeToInsert;
        } else {
            nodeToInsert.next = nodeAfter.next;
            nodeToInsert.prev = nodeAfter;
            nodeAfter.next = nodeToInsert;
            nodeToInsert.next.prev = nodeToInsert;
        }
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder("{ ");
        Node<T> node = this.head;
        while (node != null) {
            stringBuilder
                    .append(node.getValue())
                    .append(", ");
            node = node.next;
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 2);
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    class Node<V> {
        public V value;
        public Node<V> next;
        public Node<V> prev;

        public Node(final V _value) {
            value = _value;
            next = null;
            prev = null;
        }

        public V getValue() {
            return value;
        }
    }

}
