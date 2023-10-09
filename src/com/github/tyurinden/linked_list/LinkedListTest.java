package com.github.tyurinden.linked_list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

public class LinkedListTest {

    @Test
    public void testEquals() {
        LinkedList linkedList1 = new LinkedList(1, 2, 3);
        LinkedList linkedList2 = new LinkedList(1, 2, 3);
        LinkedList linkedList3 = new LinkedList(1, 3, 2);
        LinkedList linkedList4 = new LinkedList(1, 1, 1);
        LinkedList linkedList5 = new LinkedList(1, 1, 1);
        assertEquals(linkedList1, linkedList1);
        assertEquals(linkedList1, linkedList2);
        assertFalse(linkedList2.equals(linkedList3));
        assertEquals(linkedList4, linkedList5);
    }

    @Test
    public void addInTail() {
        LinkedList linkedList = new LinkedList(1, 2, 3, 4, 5);
        Node newNode = new Node(6);
        linkedList.addInTail(newNode);
        assertEquals(new LinkedList(1, 2, 3, 4, 5, 6), linkedList);
        assertEquals(newNode, linkedList.tail);
    }

    @Test
    public void addInTailWhenListIsEmpty() {
        LinkedList linkedList1 = new LinkedList();
        Node newNode = new Node(6);
        linkedList1.addInTail(newNode);
        assertEquals(new LinkedList(6), linkedList1);
        assertEquals(newNode, linkedList1.head);
        assertEquals(newNode, linkedList1.tail);
    }

    @Test
    public void find() {
        LinkedList linkedList = new LinkedList(1, 2, 3, 4, 5);
        Node newNode = new Node(6);
        linkedList.addInTail(newNode);
        assertEquals(newNode, linkedList.find(6));
        assertEquals(6, linkedList.find(6).getValue());
        assertEquals(newNode, linkedList.tail);
    }

    @Test
    public void findAll() {
        LinkedList linkedList = new LinkedList(2, 2, 3, 4, 2, 5, 6, 2, 2);
        List<Node> nodeList = new ArrayList<>();
        Node node = new Node(2);
        nodeList.add(node);
        nodeList.add(node);
        nodeList.add(node);
        nodeList.add(node);
        nodeList.add(node);
        assertEquals(5, linkedList.findAll(2).size());
        assertArrayEquals(nodeList.toArray(), linkedList.findAll(2).toArray());
    }

    @Test
    public void remove() {
        LinkedList linkedList = new LinkedList(1, 2, 3, 4, 5);
        linkedList.remove(4);
        assertEquals(4, linkedList.count());
        assertEquals(new LinkedList(1, 2, 3, 5), linkedList);
    }

    @Test
    public void removeWhenItemInHead() {
        LinkedList linkedList = new LinkedList(1, 2, 3, 4, 5);
        linkedList.remove(1);
        assertEquals(4, linkedList.count());
        assertEquals(new LinkedList(2, 3, 4, 5), linkedList);
        assertEquals(2, linkedList.head.getValue());
    }

    @Test
    public void removeWhenItemInTail() {
        LinkedList linkedList = new LinkedList(1, 2, 3, 4, 5);
        assertEquals(5, linkedList.tail.getValue());
        linkedList.remove(5);
        assertEquals(4, linkedList.count());
        assertEquals(new LinkedList(1, 2, 3, 4), linkedList);
        assertEquals(4, linkedList.tail.getValue());
    }

    @Test
    public void removeWhenListHasSameItemsInRow() {
        LinkedList linkedList = new LinkedList(1, 2, 2, 2, 5);
        assertEquals(5, linkedList.tail.getValue());
        linkedList.remove(2);
        assertEquals(4, linkedList.count());
        assertEquals(new LinkedList(1, 2, 2, 5), linkedList);
        assertEquals(5, linkedList.tail.getValue());
    }

    @Test
    public void removeWhenListHasSameItemsInRowInHead() {
        LinkedList linkedList = new LinkedList(2, 2, 2, 3, 4);
        assertEquals(2, linkedList.head.getValue());
        linkedList.remove(2);
        assertEquals(4, linkedList.count());
        assertEquals(new LinkedList(2, 2, 3, 4), linkedList);
        assertEquals(2, linkedList.head.getValue());
    }

    @Test
    public void removeWhenListHasSameItemsInRowInTail() {
        LinkedList linkedList = new LinkedList(1, 2, 3, 4, 5, 5, 5);
        assertEquals(5, linkedList.tail.getValue());
        linkedList.remove(5);
        assertEquals(6, linkedList.count());
        assertEquals(new LinkedList(1, 2, 3, 4, 5, 5), linkedList);
        assertEquals(5, linkedList.tail.getValue());
    }

    @Test
    public void removeAll() {
        LinkedList linkedList = new LinkedList(1, 2, 3, 4, 2, 5, 2, 6);
        linkedList.removeAll(2);
        assertEquals(new LinkedList(1, 3, 4, 5, 6), linkedList);
        assertEquals(1, linkedList.head.getValue());
        assertEquals(6, linkedList.tail.getValue());
    }

    @Test
    public void removeAllWhenItemsInHeadAndTailAreTheSame() {
        LinkedList linkedList = new LinkedList(1, 2, 5, 6, 1);
        assertEquals(1, linkedList.head.getValue());
        assertEquals(1, linkedList.tail.getValue());
        linkedList.removeAll(1);
        assertEquals(new LinkedList(2, 5, 6), linkedList);
        assertEquals(2, linkedList.head.getValue());
        assertEquals(6, linkedList.tail.getValue());
    }

    @Test
    public void removeAllWhenItemsInHeadAndTailAreTheSameInRow() {
        LinkedList linkedList = new LinkedList(1, 1, 1, 2, 3, 4, 5, 1, 1);
        assertEquals(1, linkedList.head.getValue());
        assertEquals(1, linkedList.tail.getValue());
        linkedList.removeAll(1);
        assertEquals(new LinkedList(2, 3, 4, 5), linkedList);
        assertEquals(2, linkedList.head.getValue());
        assertEquals(5, linkedList.tail.getValue());
    }

    @Test
    public void clear() {
        LinkedList linkedList = new LinkedList(1, 2, 3, 4, 2, 5, 6, 2);
        linkedList.clear();
        assertNull(linkedList.head);
        assertNull(linkedList.tail);
    }

    @Test
    public void count() {
        LinkedList linkedList1 = new LinkedList(1, 2, 3, 4, 2, 5, 6, 2);
        assertEquals(8, linkedList1.count());
        LinkedList linkedList2 = new LinkedList();
        assertEquals(0, linkedList2.count());
    }

    @Test
    public void toArray() {
        LinkedList linkedList1 = new LinkedList(1, 2, 3, 4);
        assertArrayEquals(new int[]{1, 2, 3, 4}, linkedList1.toArray());
        LinkedList linkedList2 = new LinkedList();
        assertEquals(0, linkedList2.toArray().length);
    }

    @Test
    public void toStringMethodWithManyElements() {
        LinkedList linkedList = new LinkedList(1, 2, 3, 4);
        assertEquals("{ 1 }, { 2 }, { 3 }, { 4 }", linkedList.toString());
    }

    @Test
    public void toStringMethodWithOneElement() {
        LinkedList linkedList = new LinkedList(1);
        assertEquals("{ 1 }", linkedList.toString());
    }

    @Test
    public void toStringMethodWithNoElements() {
        LinkedList linkedList = new LinkedList();
        assertEquals("", linkedList.toString());
    }

    @Test
    public void insertAfter() {
        LinkedList linkedList = new LinkedList(1, 2, 3);
        Node newNode = new Node(4);
        linkedList.insertAfter(linkedList.find(2), newNode);
        assertEquals(linkedList, new LinkedList(1, 2, 4, 3));
        assertEquals(linkedList, new LinkedList(1, 2, 4, 3));
        assertEquals(1, linkedList.head.getValue());
        assertEquals(3, linkedList.tail.getValue());
    }

    @Test
    public void insertAfterWhenAfterElementInTail() {
        LinkedList linkedList = new LinkedList(1, 2, 3);
        Node newNode = new Node(4);
        linkedList.insertAfter(linkedList.find(3), newNode);
        assertEquals(linkedList, new LinkedList(1, 2, 3, 4));
        assertEquals(1, linkedList.head.getValue());
        assertEquals(newNode, linkedList.tail);
    }

    @Test
    public void insertAfterWhenAfterElementIsNull() {
        LinkedList linkedList = new LinkedList(1, 2, 3);
        Node newNode = new Node(4);
        linkedList.insertAfter(null, newNode);
        assertEquals(linkedList, new LinkedList(4, 1, 2, 3));
        assertEquals(linkedList.head, newNode);
    }

    @Test
    public void insertAfterWhenListIsEmpty() {
        LinkedList linkedList = new LinkedList();
        Node node = new Node(4);
        linkedList.insertAfter(null, node);
        assertEquals(linkedList, new LinkedList(4));
        assertEquals(linkedList.head, node);
        assertEquals(linkedList.tail, node);
    }

    @Test
    public void addTwoListsByElementValues() {
        LinkedList linkedList1 = new LinkedList(1, 2, 3);
        LinkedList linkedList2 = new LinkedList(1, 2, 3);
        linkedList1.addTwoListsByElementValues(linkedList1, linkedList2);
        assertEquals(new LinkedList(2, 4, 6), linkedList1.addTwoListsByElementValues(linkedList1, linkedList2));
    }

}
