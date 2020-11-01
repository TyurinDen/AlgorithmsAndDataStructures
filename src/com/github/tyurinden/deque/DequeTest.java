package com.github.tyurinden.deque;

import org.junit.Assert;
import org.junit.Test;

public class DequeTest {

    @Test
    public void makeDequeWithIntType() {
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

}