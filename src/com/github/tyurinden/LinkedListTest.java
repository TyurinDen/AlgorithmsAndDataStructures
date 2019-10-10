package com.github.tyurinden;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class LinkedListTest {

    @Test
    public void testEquals() {
        LinkedList linkedList1 = new LinkedList(1, 2, 3);
        LinkedList linkedLis2 = new LinkedList(1, 2, 3);
        assertEquals(linkedList1, linkedLis2);
    }

    @Test
    public void addInTail() {
        LinkedList linkedList1 = new LinkedList(1, 2, 3, 4, 5);
        linkedList1.addInTail(new Node(6));
        Assert.assertEquals(linkedList1, new LinkedList(1, 2, 3, 4, 5, 6));
    }

    @Test
    public void find() {
        LinkedList linkedList = new LinkedList(1, 2, 3);
        Assert.assertEquals(linkedList.find(2).getValue(), new Node(2).getValue());
    }

    @Test
    public void findAll() {
        LinkedList linkedList = new LinkedList(1, 2, 3, 4, 2, 5, 6, 2);
        assertEquals(3, linkedList.findAll(2).size());
    }

    @Test
    public void remove() {
        LinkedList linkedList = new LinkedList(1, 2, 3, 4, 5);
        linkedList.remove(4);
        assertEquals(linkedList, new LinkedList(1, 2, 3, 5));
        linkedList.remove(1);
        assertEquals(linkedList, new LinkedList(2, 3, 5));
        linkedList.remove(5);
        assertEquals(linkedList, new LinkedList(2, 3));
    }

    @Test
    public void removeAll() {
        LinkedList linkedList = new LinkedList(1, 2, 3, 4, 2, 5, 6, 2);
        linkedList.removeAll(2);
        assertEquals(linkedList, new LinkedList(1, 3, 4, 5, 6));
    }

    @Test
    public void removeAllElementsInHeadAndTail() {
        LinkedList linkedList = new LinkedList(1, 2, 5, 6, 1);
        linkedList.removeAll(1);
        assertEquals(linkedList, new LinkedList(2, 5, 6));
    }

    @Test
    public void clear() {
        LinkedList linkedList = new LinkedList(1, 2, 3, 4, 2, 5, 6, 2);
        linkedList.clear();
        assertEquals(linkedList, new LinkedList());
    }

    @Test
    public void count() {
        LinkedList linkedList = new LinkedList(1, 2, 3, 4, 2, 5, 6, 2);
        assertEquals(linkedList.count(), 8);
    }

    @Test
    public void toArray() {
        LinkedList linkedList = new LinkedList(1, 2, 3, 4);
        assertArrayEquals(linkedList.toArray(), new int[]{1, 2, 3, 4});
    }

    @Test
    public void toStringMethodWithTwoElements() {
        LinkedList linkedList = new LinkedList(1, 2);
        assertEquals(linkedList.toString(), "{ 1 }, { 2 }");
    }

    @Test
    public void toStringMethodWithOneElement() {
        LinkedList linkedList = new LinkedList(1);
        assertEquals(linkedList.toString(), "{ 1 }");
    }

    @Test
    public void insertAfter() {
        LinkedList linkedList = new LinkedList(1, 2, 3);
        linkedList.insertAfter(linkedList.find(2), new Node(4));
        assertEquals(linkedList, new LinkedList(1, 2, 4, 3));
    }

    @Test
    public void insertAfterAfterElementIsNull() {
        LinkedList linkedList = new LinkedList(1, 2, 3);
        linkedList.insertAfter(null, new Node(4));
        assertEquals(linkedList, new LinkedList(4, 1, 2, 3));
    }

    @Test
    public void addTwoLists() {
        LinkedList linkedList1 = new LinkedList(1, 2, 3);
        LinkedList linkedList2 = new LinkedList(1, 2, 3);
        linkedList1.addTwoLists(linkedList1, linkedList2);
        assertEquals(linkedList1.addTwoLists(linkedList1, linkedList2), new LinkedList(2, 4, 6));
    }

}