package com.github.tyurinden.linked_list2;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class LinkedList2Test {

    @Test
    public void addInTail() {
        LinkedList2 linkedList = new LinkedList2(1, 2, 3);
        Node newNode = new Node(4);
        linkedList.addInTail(newNode);
        assertEquals(newNode, linkedList.tail);
        assertEquals(linkedList.find(3), newNode.prev);
        assertEquals(newNode, linkedList.find(3).next);
        assertNull(newNode.next);
    }

    @Test
    public void find() {
        LinkedList2 linkedList = new LinkedList2(1, 2, 3, 4, 5);
        Node node = linkedList.find(3);
        assertEquals(node, linkedList.find(3));
        assertNull(linkedList.find(10));
        assertEquals(1, linkedList.find(1).value);
        assertEquals(5, linkedList.find(5).value);
    }

    @Test
    public void findAll() {
        LinkedList2 linkedList = new LinkedList2(3, 2, 3, 4, 5, 3, 4, 2, 3, 3);
        List<Node> nodeList = new ArrayList<>();
        nodeList.add(new Node(3));
        nodeList.add(new Node(3));
        nodeList.add(new Node(3));
        nodeList.add(new Node(3));
        nodeList.add(new Node(3));
        assertArrayEquals(nodeList.toArray(), linkedList.findAll(3).toArray());
    }

    @Test
    public void findAllWhenListIsEmpty() {
        LinkedList2 linkedList = new LinkedList2();
        assertArrayEquals(new ArrayList<Node>().toArray(), linkedList.findAll(3).toArray());
    }

    @Test
    public void findAllWhenSearchedItemIsAbsent() {
        LinkedList2 linkedList = new LinkedList2(1, 2, 3, 4);
        assertArrayEquals(new ArrayList<Node>().toArray(), linkedList.findAll(5).toArray());
    }

    @Test
    public void remove() {
        LinkedList2 linkedList = new LinkedList2(1, 2, 3, 3, 5);
        assertTrue(linkedList.remove(3));
        assertEquals(4, linkedList.count());
        assertEquals(new LinkedList2(1, 2, 3, 5), linkedList);
    }

    @Test
    public void removeWhenRemovedItemInHead() {
        LinkedList2 linkedList = new LinkedList2(1, 2, 3, 4, 5);
        assertTrue(linkedList.remove(1));
        assertEquals(4, linkedList.count());
        assertEquals(2, linkedList.head.value);
        assertNull(linkedList.head.prev);
    }

    @Test
    public void removeWhenRemovedItemInTail() {
        LinkedList2 linkedList = new LinkedList2(1, 2, 3, 4, 5);
        assertTrue(linkedList.remove(5));
        assertEquals(4, linkedList.count());
        assertEquals(4, linkedList.tail.value);
        assertNull(linkedList.tail.next);
    }

    @Test
    public void removeWhenRemovedItemIsAbsent() {
        LinkedList2 linkedList = new LinkedList2(1, 2, 3, 4, 5);
        assertFalse(linkedList.remove(6));
        assertEquals(5, linkedList.count());
        assertEquals(5, linkedList.tail.value);
        assertEquals(1, linkedList.head.value);
    }

    @Test
    public void removeWhenRemovedItemIsOnlyOneInWholeList() {
        LinkedList2 linkedList = new LinkedList2(1);
        assertFalse(linkedList.remove(1));
        assertNull(linkedList.head);
        assertNull(linkedList.tail);
    }

    @Test
    public void removeAll() {
        LinkedList2 linkedList = new LinkedList2(1, 2, 3, 3, 5);
        linkedList.removeAll(3);
        assertEquals(3, linkedList.count());
        assertEquals(new LinkedList2(1, 2, 5), linkedList);
        assertEquals(1, linkedList.head.value);
        assertEquals(5, linkedList.tail.value);
    }

    @Test
    public void removeAllWhenRemovedItemsInHead() {
        LinkedList2 linkedList = new LinkedList2(1, 1, 2, 3, 4);
        linkedList.removeAll(1);
        assertEquals(3, linkedList.count());
        assertEquals(new LinkedList2(2, 3, 4), linkedList);
        assertEquals(2, linkedList.head.value);
        assertEquals(4, linkedList.tail.value);
        assertNull(linkedList.head.prev);
    }

    @Test
    public void removeAllWhenRemovedItemsInTail() {
        LinkedList2 linkedList = new LinkedList2(1, 2, 3, 3, 3);
        linkedList.removeAll(3);
        assertEquals(2, linkedList.count());
        assertEquals(new LinkedList2(1, 2), linkedList);
        assertEquals(1, linkedList.head.value);
        assertEquals(2, linkedList.tail.value);
        assertNull(linkedList.tail.next);
    }

    @Test
    public void removeAllWhenRemovedItemsAreInHeadAndTail() {
        LinkedList2 linkedList = new LinkedList2(1, 1, 2, 1, 1, 3, 1, 4, 1, 1);
        linkedList.removeAll(1);
        assertEquals(3, linkedList.count());
        assertEquals(new LinkedList2(2, 3, 4), linkedList);
        assertEquals(2, linkedList.head.value);
        assertEquals(4, linkedList.tail.value);
        assertNull(linkedList.head.prev);
        assertNull(linkedList.tail.next);
    }

    @Test
    public void removeAllWhenInListOnlyOneElement() {
        LinkedList2 linkedList = new LinkedList2(1);
        linkedList.removeAll(1);
        assertEquals(0, linkedList.count());
        assertNull(linkedList.head);
        assertNull(linkedList.tail);
    }

    @Test
    public void removeAllWhenInListNoSuchItems() {
        LinkedList2 linkedList = new LinkedList2(1, 2, 3, 4);
        linkedList.removeAll(5);
        assertEquals(4, linkedList.count());
        assertEquals(1, linkedList.head.value);
        assertEquals(4, linkedList.tail.value);
    }

    @Test
    public void clear() {
        LinkedList2 linkedList = new LinkedList2(1, 2, 3);
        linkedList.clear();
        assertNull(linkedList.head);
        assertNull(linkedList.tail);
    }

    @Test
    public void count() {
        LinkedList2 linkedList = new LinkedList2(1, 2, 3, 4);
        assertEquals(4, linkedList.count());
        linkedList.removeAll(3);
        assertEquals(3, linkedList.count());
    }

    @Test
    public void insertAfter() {
        LinkedList2 linkedList = new LinkedList2(1, 2, 3, 4, 5);
        Node newNode = new Node(33);
        linkedList.insertAfter(linkedList.find(3), newNode);
        assertEquals(6, linkedList.count());
        assertEquals(new LinkedList2(1, 2, 3, 33, 4, 5), linkedList);
        assertEquals(newNode, linkedList.find(3).next);
        assertEquals(newNode, linkedList.find(4).prev);
        assertEquals(newNode.next, linkedList.find(4));
        assertEquals(newNode.prev, linkedList.find(3));
    }

    @Test
    public void insertAfterWhenInsertAfterIsNull() {
        LinkedList2 linkedList = new LinkedList2(1, 2, 3, 4, 5);
        Node newNode = new Node(11);
        linkedList.insertAfter(null, newNode);
        assertEquals(6, linkedList.count());
        assertEquals(new LinkedList2(11, 1, 2, 3, 4, 5), linkedList);
        assertEquals(newNode, linkedList.head);
        assertEquals(linkedList.find(1), newNode.next);
        assertNull(newNode.prev);
        assertEquals(newNode, linkedList.find(1).prev);
    }

    @Test
    public void insertAfterWhenListIsEmpty() {
        LinkedList2 linkedList = new LinkedList2();
        Node newNode = new Node(11);
        linkedList.insertAfter(null, newNode);
        assertEquals(1, linkedList.count());
        assertEquals(new LinkedList2(11), linkedList);
        assertEquals(newNode, linkedList.head);
        assertEquals(newNode, linkedList.tail);
        assertNull(newNode.prev);
        assertNull(newNode.next);
    }

    @Test
    public void insertAfterWhenInsertAfterIsInTail() {
        LinkedList2 linkedList = new LinkedList2(1, 2, 3, 4, 5);
        Node newNode = new Node(55);
        linkedList.insertAfter(linkedList.find(5), newNode);
        assertEquals(6, linkedList.count());
        assertEquals(new LinkedList2(1, 2, 3, 4, 5, 55), linkedList);
        assertEquals(newNode, linkedList.tail);
        assertEquals(linkedList.find(5).next, newNode);
        assertNull(newNode.next);
        assertEquals(linkedList.find(5), newNode.prev);
    }

}