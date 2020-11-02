package com.github.tyurinden.linked_list2;

import java.util.*;

public class LinkedList2 {
    public Node head;
    public Node tail;

    public LinkedList2() {
        head = null;
        tail = null;
    }

    public LinkedList2(int... ints) {
        for (int i : ints) {
            addInTail(new Node(i));
        }
    }

    public void addInTail(Node _item) {
        if (head == null) {
            this.head = _item;
            this.head.next = null;
            this.head.prev = null;
        } else {
            this.tail.next = _item;
            _item.prev = tail;
        }
        this.tail = _item;
    }

    public Node find(int _value) {
        Node node = this.head;
        while (node != null) {
            if (node.value == _value) {
                return node;
            }
            node = node.next;
        }
        return null;
    }

    public ArrayList<Node> findAll(int _value) {
        ArrayList<Node> nodes = new ArrayList<>();
        Node node = this.head;
        while (node != null) {
            if (node.value == _value) nodes.add(node);
            node = node.next;
        }
        return nodes;
    }

    public boolean remove(int _value) {
        Node node;
        if ((node = this.find(_value)) != null) {
            if (this.head == this.tail) {
                this.clear();
                return true;
            }
            if (node == this.head) {
                this.head = node.next;
                this.head.prev = null;
                return true;
            }
            if (node == this.tail) {
                this.tail = node.prev;
                this.tail.next = null;
                return true;
            }
            node.prev.next = node.next;
            node.next.prev = node.prev;
            return true;
        }
        return false;
    }

    public void removeAll(int _value) {
        Node node = this.head;
        while (node != null) {
            if (node.value == _value) {
                if (this.head == this.tail) {
                    clear();
                    return;
                }
                if (node == this.head) {
                    this.head = node.next;
                    this.head.prev = null;
                    node = node.next;
                    continue;
                }
                if (node == this.tail) {
                    this.tail = node.prev;
                    this.tail.next = null;
                    return;
                }
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }
            node = node.next;
        }
    }

    public void clear() {
        this.head = null;
        this.tail = null;
    }

    public int count() {
        Node node = this.head;
        int i = 0;
        while (node != null) {
            i++;
            node = node.next;
        }
        return i;
    }

    public void insertAfter(Node _nodeAfter, Node _nodeToInsert) {
        if (_nodeToInsert == null) return;

        if (_nodeAfter == null) {
            if (this.head == null) {
                addInTail(_nodeToInsert);
                return;
            }
            _nodeToInsert.next = this.head;
            this.head.prev = _nodeToInsert;
            this.head = _nodeToInsert;
            return;
        }
        if (_nodeAfter == this.tail) {
            addInTail(_nodeToInsert);
            return;
        }
        _nodeToInsert.next = _nodeAfter.next;
        _nodeAfter.next.prev = _nodeToInsert;
        _nodeAfter.next = _nodeToInsert;
        _nodeToInsert.prev = _nodeAfter;
    }

    @Override
    public boolean equals(Object o) {
        //списки равны только если равны их размеры и все их соответствующие элементы, т.е. списки 1,2,3 и 1,3,2 не равны.
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinkedList2 other = (LinkedList2) o;

        if (this.count() != other.count()) return false;
        Node thisNode = this.head;
        Node otherNode = other.head;
        while (thisNode != null) {
            if (thisNode.value != otherNode.value) return false;
            thisNode = thisNode.next;
            otherNode = otherNode.next;
        }
        return true;
    }

}

class Node {
    public int value;
    public Node next;
    public Node prev;

    public Node(int _value) {
        value = _value;
        next = null;
        prev = null;
    }

    public int getValue() {
        return value;
    }

    public Node getNext() {
        return next;
    }

    public Node getPrev() {
        return prev;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return value == node.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

}
