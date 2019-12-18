package com.github.tyurinden.queue;

import org.junit.Test;

import static org.junit.Assert.*;

public class QueueTest {

    @Test
    public void insertionAndRemovalInOrder() {
        Queue<Integer> integerQueue = new Queue<>();
        assertEquals(0, integerQueue.size());

        integerQueue.enqueue(1);
        integerQueue.enqueue(2);
        integerQueue.enqueue(3);
        assertEquals(3, integerQueue.size());

        assertEquals(new Integer(3), integerQueue.dequeue());
        assertEquals(2, integerQueue.size());
        assertEquals(new Integer(2), integerQueue.dequeue());
        assertEquals(1, integerQueue.size());
        assertEquals(new Integer(1), integerQueue.dequeue());
        assertEquals(0, integerQueue.size());

        assertNull(integerQueue.dequeue());
    }

    @Test
    public void insertionAndRemovalAtRandom() {
        Queue<Integer> integerQueue = new Queue<>();
        assertEquals(0, integerQueue.size());

        integerQueue.enqueue(1);
        integerQueue.enqueue(2);
        integerQueue.enqueue(3);
        assertEquals(3, integerQueue.size());

        assertEquals(new Integer(1), integerQueue.dequeue());
        assertEquals(2, integerQueue.size());

        integerQueue.enqueue(0);
        integerQueue.enqueue(-1);
        assertEquals(4, integerQueue.size());

        assertEquals(new Integer(2), integerQueue.dequeue());
        assertEquals(3, integerQueue.size());

        assertEquals(new Integer(3), integerQueue.dequeue());
        assertEquals(2, integerQueue.size());

        assertEquals(new Integer(0), integerQueue.dequeue());
        assertEquals(new Integer(-1), integerQueue.dequeue());
        assertEquals(0, integerQueue.size());
        assertNull(integerQueue.dequeue());
    }

}