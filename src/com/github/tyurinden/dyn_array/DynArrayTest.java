package com.github.tyurinden.dyn_array;

import org.junit.Test;

import static org.junit.Assert.*;

public class DynArrayTest {

    @Test
    public void makeArray() {
        DynArray<Integer> integerDynArray = new DynArray<>(Integer.class);
        assertEquals(16, integerDynArray.capacity);
        assertEquals(0, integerDynArray.count);
        integerDynArray.appendMany(1, 2, 3);
        assertEquals(3, integerDynArray.count);
        assertEquals(16, integerDynArray.capacity);
        assertEquals(new Integer(1), integerDynArray.getItem(0));
        assertEquals(new Integer(2), integerDynArray.getItem(1));
        assertEquals(new Integer(3), integerDynArray.getItem(2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void makeArrayWhenCapacityOfArrayIncreases() {
        DynArray<Integer> integerDynArray = new DynArray<>(Integer.class);
        integerDynArray.appendMany(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
        assertEquals(16, integerDynArray.count);
        integerDynArray.append(16);
        assertEquals(17, integerDynArray.count);
        assertEquals(32, integerDynArray.capacity);
        assertEquals(new Integer(0), integerDynArray.getItem(0));
        assertEquals(new Integer(15), integerDynArray.getItem(15));
        assertEquals(new Integer(16), integerDynArray.getItem(16));
        integerDynArray.getItem(integerDynArray.count); // throws IllegalArgumentException
    }

    @Test
    public void getItem() {
    }

    @Test
    public void append() {
    }

    @Test(expected = IllegalArgumentException.class)
    public void insert() {
        DynArray<Integer> integerDynArray = new DynArray<>(Integer.class);
        integerDynArray.appendMany(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        assertEquals(16, integerDynArray.capacity);
        integerDynArray.insert(44, 4);
        assertEquals(11, integerDynArray.count);
        assertEquals(new Integer(44), integerDynArray.getItem(4));
        assertEquals(new Integer(4), integerDynArray.getItem(5));
        assertEquals(new Integer(5), integerDynArray.getItem(6));
        assertEquals(new Integer(6), integerDynArray.getItem(7));
        assertEquals(new Integer(7), integerDynArray.getItem(8));
        assertEquals(new Integer(8), integerDynArray.getItem(9));
        assertEquals(new Integer(9), integerDynArray.getItem(10));
        integerDynArray.getItem(11); // throws IllegalArgumentException
    }

    @Test(expected = IllegalArgumentException.class)
    public void insertWhenCapacityOfArrayIncreases() {
        DynArray<Integer> integerDynArray = new DynArray<>(Integer.class, 5);
        integerDynArray.appendMany(0, 1, 2, 3, 4);
        assertEquals(5, integerDynArray.count);
        assertEquals(5, integerDynArray.capacity);
        integerDynArray.insert(22, 2);
        assertEquals(6, integerDynArray.count);
        assertEquals(10, integerDynArray.capacity);
        assertEquals(new Integer(22), integerDynArray.getItem(2));
        assertEquals(new Integer(2), integerDynArray.getItem(3));
        assertEquals(new Integer(3), integerDynArray.getItem(4));
        assertEquals(new Integer(4), integerDynArray.getItem(5));
        integerDynArray.getItem(6); // throws IllegalArgumentException
    }

    @Test(expected = IllegalArgumentException.class)
    public void insertWhenArrayIsEmpty() {
        DynArray<Integer> integerDynArray = new DynArray<>(Integer.class);
        assertEquals(0, integerDynArray.count);
        assertEquals(16, integerDynArray.capacity);
        integerDynArray.insert(22, 0); // throws IllegalArgumentException
    }

    @Test
    public void remove() {
    }
}