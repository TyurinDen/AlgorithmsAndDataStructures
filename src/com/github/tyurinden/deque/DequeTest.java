package com.github.tyurinden.deque;

import org.junit.Assert;
import org.junit.Test;

public class DequeTest {

    @Test
    public void shouldReturnCorrectDequeSize() {
        final Deque<Integer> deque = new Deque<>();
        Assert.assertEquals(0, deque.size());

        deque.addFront(1);
        Assert.assertEquals(1, deque.size());

        deque.addTail(2);
        Assert.assertEquals(2, deque.size());

        deque.removeFront();
        Assert.assertEquals(1, deque.size());

        deque.removeTail();
        Assert.assertEquals(0, deque.size());
    }

    @Test
    public void shouldReturnCorrectDequeSizeWhenAddedToHead() {
        final Deque<Integer> deque = new Deque<>();
        Assert.assertEquals(0, deque.size());

        deque.addFront(1);
        deque.addFront(2);
        deque.addFront(3);
        deque.addFront(4);
        deque.addFront(5);
        Assert.assertEquals(5, deque.size());

        deque.removeFront();
        deque.removeFront();
        deque.removeFront();
        deque.removeFront();
        deque.removeFront();
        Assert.assertEquals(0, deque.size());
    }

    @Test
    public void shouldReturnCorrectDequeSizeWhenAddedToTail() {
        final Deque<Integer> deque = new Deque<>();
        Assert.assertEquals(0, deque.size());

        deque.addTail(1);
        deque.addTail(1);
        deque.addTail(1);
        deque.addTail(1);
        deque.addTail(1);
        Assert.assertEquals(5, deque.size());

        deque.removeTail();
        deque.removeTail();
        deque.removeTail();
        deque.removeTail();
        deque.removeTail();
        Assert.assertEquals(0, deque.size());
    }

    @Test
    public void shouldReturnCorrectDequeSizeWhenAddedToTailAndHead() {
        final Deque<Integer> deque = new Deque<>();
        Assert.assertEquals(0, deque.size());

        deque.addTail(1);
        deque.addFront(2);
        deque.addTail(3);
        deque.addFront(4);
        deque.addTail(5);
        deque.addFront(6);
        deque.addTail(7);
        deque.addFront(8);
        deque.addTail(9);
        deque.addFront(10);
        deque.addTail(11);
        Assert.assertEquals(11, deque.size());
    }

    @Test
    public void shouldReturnCorrectValuesWhenAddedToTailAndHead() {
        final Deque<Integer> deque = new Deque<>();
        Assert.assertEquals(0, deque.size());

        Assert.assertNull(deque.removeFront());
        Assert.assertNull(deque.removeTail());

        deque.addTail(1);
        deque.addFront(2);
        deque.addTail(3);
        deque.addFront(4);
        deque.addTail(5);
        deque.addFront(6);
        Assert.assertEquals(6, deque.size());

        Assert.assertEquals(Integer.valueOf(5), deque.removeTail());
        Assert.assertEquals(Integer.valueOf(6), deque.removeFront());
        Assert.assertEquals(Integer.valueOf(3), deque.removeTail());
        Assert.assertEquals(Integer.valueOf(4), deque.removeFront());
        Assert.assertEquals(Integer.valueOf(1), deque.removeTail());
        Assert.assertEquals(Integer.valueOf(2), deque.removeFront());

        Assert.assertEquals(0, deque.size());
        Assert.assertNull(deque.removeFront());
        Assert.assertNull(deque.removeTail());
    }

    @Test
    public void shouldReturnCorrectValuesWhenAddedToTail() {
        final Deque<Integer> deque = new Deque<>();
        Assert.assertEquals(0, deque.size());

        Assert.assertNull(deque.removeFront());
        Assert.assertNull(deque.removeTail());

        for (int i = 0; i < 1_000_000; i++) {
            deque.addTail(i);
        }
        Assert.assertEquals(1_000_000, deque.size());

        for (int i = 999_999; i >= 0; i--) {
            Assert.assertEquals(Integer.valueOf(i), deque.removeTail());
        }

        Assert.assertEquals(0, deque.size());
        Assert.assertNull(deque.removeFront());
        Assert.assertNull(deque.removeTail());
    }

    @Test
    public void shouldReturnCorrectValuesWhenAddedToHead() {
        final Deque<Integer> deque = new Deque<>();
        Assert.assertEquals(0, deque.size());

        Assert.assertNull(deque.removeFront());
        Assert.assertNull(deque.removeTail());

        for (int i = 0; i < 1_000_000; i++) {
            deque.addFront(i);
        }
        Assert.assertEquals(1_000_000, deque.size());

        for (int i = 999_999; i >= 0; i--) {
            Assert.assertEquals(Integer.valueOf(i), deque.removeFront());
        }

        Assert.assertEquals(0, deque.size());
        Assert.assertNull(deque.removeFront());
        Assert.assertNull(deque.removeTail());
    }


}