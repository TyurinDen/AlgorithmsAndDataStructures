package com.github.tyurinden;

import java.util.*;

public class LinkedList {
    private Node head;
    private Node tail;

    public LinkedList() {
        head = null;
        tail = null;
    }

    public LinkedList(int... ints) {
        for (int anInt : ints) {
            addInTail(new Node(anInt));
        }
    }

    public void addInTail(Node item) {
        if (this.head == null)
            this.head = item;
        else
            this.tail.next = item;
        this.tail = item;
    }

    public Node find(int value) {
        Node node = this.head;
        while (node != null) {
            if (node.value == value)
                return node;
            node = node.next;
        }
        return null;
    }

    public ArrayList<Node> findAll(int _value) {
        ArrayList<Node> nodes = new ArrayList<>();
        Node node = this.head;
        while (node != null) {
            if (node.value == _value) {
                nodes.add(node);
            }
            node = node.next;
        }
        return nodes;
    }

    public int[] toArray() {
        if (count() == 0) return new int[0];
        int[] ints = new int[count()];
        int i = 0;
        Node node = this.head;
        while (node != null) {
            ints[i] = node.value;
            node = node.next;
            i++;
        }
        return ints;
    }

    public boolean remove(int _value) {
        Node previous = this.head;
        Node current = this.head;
        while (current != null) {
            if (current.value == _value) {
                if (this.head == this.tail) {
                    this.head = null;
                    this.tail = null;
                    return true;
                }
                if (current == this.head) {
                    this.head = current.next;
                    return true;
                }
                if (current == this.tail) {
                    this.tail = previous;
                    previous.next = null;
                    return true;
                }
                previous.next = current.next;
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    public void removeAll(int _value) {
        Node prev = this.head;
        Node current = this.head;
        while (current != null) {
            if (current.value == _value) {
                if (this.head == this.tail) {
                    this.head = null;
                    this.tail = null;
                    return;
                }
                if (current == this.head) {
                    this.head = current.next;
                    prev = this.head;
                    current = current.next;
                    continue;
                }
                if (current == this.tail) {
                    this.tail = prev;
                    prev.next = null;
                    return;
                }
                prev.next = current.next;
                current = current.next;
            } else {
                prev = current;
                current = current.next;
            }
        }
    }

    public void clear() {
        this.head = null;
        this.tail = null;
    }

    public int count() {
        int i = 0;
        Node node = this.head;
        while (node != null) {
            i++;
            node = node.next;
        }
        return i;
    }

    public void insertAfter(Node _nodeAfter, Node _nodeToInsert) {
        if (_nodeToInsert == null) {
            return;
        }
        if (_nodeAfter == null) {
            _nodeToInsert.next = this.head;
            this.head = _nodeToInsert;
            return;
        }
        _nodeToInsert.next = _nodeAfter.next;
        _nodeAfter.next = _nodeToInsert;
    }

    public LinkedList addTwoLists(LinkedList lList1, LinkedList lList2) {
        LinkedList resultList = new LinkedList();
        if (lList1.count() != lList2.count()) return resultList;
        Node lList1Node = lList1.head;
        Node lList2Node = lList2.head;
        while (lList1Node != null) {
            resultList.addInTail(new Node(lList1Node.value + lList2Node.value));
            lList1Node = lList1Node.next;
            lList2Node = lList2Node.next;
        }
        return resultList;
    }

    @Override
    public String toString() {
        if (this.count() == 0) return "";

        StringBuilder stringBuilder = new StringBuilder();
        Node node = this.head;
        while (node != null) {
            stringBuilder.append("{ ")
                    .append(node.value)
                    .append(" }, ");
            node = node.next;
        }
        return stringBuilder.substring(0, stringBuilder.length() - 2);
    }

    @Override
    public boolean equals(Object o) {
        //списки равны только если равны их размеры и все их соответстующие элементы, т.е. списки 1,2,3 и 1,3,2 не равны.
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinkedList other = (LinkedList) o;

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

    @Override
    public int hashCode() {
        return Objects.hash(head, tail);
    }

}

class Node {
    int value;
    Node next;

    public Node(int _value) {
        value = _value;
        next = null;
    }

    public int getValue() {
        return value;
    }

    public Node getNext() {
        return next;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

}
