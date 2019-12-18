package com.github.tyurinden.stack;

import org.junit.Test;

import static org.junit.Assert.*;

public class StackTest {

    @Test
    public void pushAndPopAndSize() {
        Stack<Integer> integerStack = new Stack<>();
        assertEquals(0, integerStack.size());
        assertNull(integerStack.pop());
        assertNull(integerStack.peek());
        integerStack.push(1);
        integerStack.push(2);
        integerStack.push(3);
        assertEquals(3, integerStack.size());

        assertEquals(new Integer(3), integerStack.pop());
        assertEquals(new Integer(2), integerStack.pop());
        assertEquals(new Integer(1), integerStack.pop());
        assertEquals(0, integerStack.size());
        assertNull(integerStack.pop());
        assertNull(integerStack.peek());

        integerStack.push(11);
        assertEquals(1, integerStack.size());
        integerStack.push(22);
        assertEquals(2, integerStack.size());
        assertEquals(new Integer(22), integerStack.pop());
        assertEquals(1, integerStack.size());
        assertEquals(new Integer(11), integerStack.pop());
        assertEquals(0, integerStack.size());
    }

    @Test
    public void peek() {
        Stack<Integer> integerStack = new Stack<>();
        integerStack.push(1);
        integerStack.push(2);
        integerStack.push(3);
        assertEquals(3, integerStack.size());

        assertEquals(new Integer(3), integerStack.peek());
        assertEquals(3, integerStack.size());
        assertEquals(new Integer(3), integerStack.pop());
        assertEquals(2, integerStack.size());
        assertEquals(new Integer(2), integerStack.peek());
        assertEquals(2, integerStack.size());

        assertEquals(new Integer(2), integerStack.pop());
        assertEquals(1, integerStack.size());
        assertEquals(new Integer(1), integerStack.peek());
        assertEquals(1, integerStack.size());
        assertEquals(new Integer(1), integerStack.pop());
        assertEquals(0, integerStack.size());
        assertNull(integerStack.peek());
        assertNull(integerStack.pop());
    }

    @Test
    public void size() {
        Stack<Integer> integerStack = new Stack<>();
        assertEquals(0, integerStack.size());

        integerStack.push(1);
        integerStack.push(2);
        integerStack.push(3);
        assertEquals(3, integerStack.size());

        integerStack.pop();
        assertEquals(2, integerStack.size());

        integerStack.pop();
        assertEquals(1, integerStack.size());

        integerStack.pop();
        assertEquals(0, integerStack.size());
    }

    @Test
    public void trimToSizeAndPushAgain() {
        Stack<Integer> integerStack = new Stack<>();
        assertEquals(0, integerStack.size());
        for (int i = 0; i < 1000; i++) {
            integerStack.push(i);
        }
        assertEquals(1369, integerStack.getCapacity());

        while (integerStack.size() > 0) {
            integerStack.pop();
        }
        assertEquals(1369, integerStack.getCapacity());
        assertEquals(0, integerStack.size());

        integerStack.trimToSize();
        assertEquals(16, integerStack.getCapacity());
        assertEquals(0, integerStack.size());

        for (int i = 0; i < 20; i++) {
            integerStack.push(i);
        }
        assertEquals(20, integerStack.size());
        assertEquals(24, integerStack.getCapacity());

        integerStack.trimToSize();
        assertEquals(20, integerStack.size());
        assertEquals(20, integerStack.getCapacity());

    }

}