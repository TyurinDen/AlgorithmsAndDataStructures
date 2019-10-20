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
        assertEquals(1, integerDynArray.getItem(0).intValue());
        assertEquals(2, integerDynArray.getItem(1).intValue());
        assertEquals(3, integerDynArray.getItem(2).intValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void makeArrayWhenCapacityOfArrayIncreases() {
        DynArray<Integer> integerDynArray = new DynArray<>(Integer.class);
        integerDynArray.appendMany(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
        assertEquals(16, integerDynArray.count);
        integerDynArray.append(16);
        assertEquals(17, integerDynArray.count);
        assertEquals(32, integerDynArray.capacity);
        assertEquals(0, integerDynArray.getItem(0).intValue());
        assertEquals(15, integerDynArray.getItem(15).intValue());
        assertEquals(16, integerDynArray.getItem(16).intValue());
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
        assertEquals(44, integerDynArray.getItem(4).intValue());
        assertEquals(4, integerDynArray.getItem(5).intValue());
        assertEquals(5, integerDynArray.getItem(6).intValue());
        assertEquals(6, integerDynArray.getItem(7).intValue());
        assertEquals(7, integerDynArray.getItem(8).intValue());
        assertEquals(8, integerDynArray.getItem(9).intValue());
        assertEquals(9, integerDynArray.getItem(10).intValue());
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
        assertEquals(22, integerDynArray.getItem(2).intValue());
        assertEquals(2, integerDynArray.getItem(3).intValue());
        assertEquals(3, integerDynArray.getItem(4).intValue());
        assertEquals(4, integerDynArray.getItem(5).intValue());
        integerDynArray.getItem(6); // throws IllegalArgumentException
    }

    @Test(expected = IllegalArgumentException.class)
    public void insertWhenArrayIsFull() {
        DynArray<Integer> integerDynArray = new DynArray<>(Integer.class, 5);
        integerDynArray.appendMany(0, 1, 2, 3, 4);
        assertEquals(5, integerDynArray.count);
        assertEquals(5, integerDynArray.capacity);
        integerDynArray.insert(44, integerDynArray.count);
        assertEquals(10, integerDynArray.capacity);
        assertEquals(6, integerDynArray.count);
        assertEquals(44, integerDynArray.getItem(6).intValue());
        assertEquals(0, integerDynArray.getItem(0).intValue());
        integerDynArray.getItem(6); // throws IllegalArgumentException
    }

    @Test
    public void insertWhenArrayIsEmpty() {
        DynArray<Integer> integerDynArray = new DynArray<>(Integer.class);
        assertEquals(0, integerDynArray.count);
        assertEquals(16, integerDynArray.capacity);
        integerDynArray.insert(22, 0);
        assertEquals(1, integerDynArray.count);
        assertEquals(22, integerDynArray.getItem(0).intValue());
    }

    @Test
    public void remove() {
        DynArray<Integer> integerDynArray = new DynArray<>(Integer.class);
        integerDynArray.appendMany(0, 1, 2, 3, 4);
        assertEquals(5, integerDynArray.count);
        assertEquals(16, integerDynArray.capacity);
        integerDynArray.remove(2);
        assertEquals(4, integerDynArray.count);
        assertEquals(0, integerDynArray.getItem(0).intValue());
        assertEquals(1, integerDynArray.getItem(1).intValue());
        assertEquals(3, integerDynArray.getItem(2).intValue());
        assertEquals(4, integerDynArray.getItem(3).intValue());
    }

    @Test
    public void removeWhenRemovedItemIsLast() {
        DynArray<Integer> integerDynArray = new DynArray<>(Integer.class);
        integerDynArray.appendMany(0, 1, 2, 3, 4);
        assertEquals(5, integerDynArray.count);
        assertEquals(16, integerDynArray.capacity);
        integerDynArray.remove(4);
        assertEquals(4, integerDynArray.count);
        assertEquals(0, integerDynArray.getItem(0).intValue());
        assertEquals(1, integerDynArray.getItem(1).intValue());
        assertEquals(2, integerDynArray.getItem(2).intValue());
        assertEquals(3, integerDynArray.getItem(3).intValue());
    }

    @Test
    public void removeWhenRemovedItemIsFirst() {
        DynArray<Integer> integerDynArray = new DynArray<>(Integer.class);
        integerDynArray.appendMany(0, 1, 2, 3, 4);
        assertEquals(5, integerDynArray.count);
        assertEquals(16, integerDynArray.capacity);
        integerDynArray.remove(0);
        assertEquals(4, integerDynArray.count);
        assertEquals(1, integerDynArray.getItem(0).intValue());
        assertEquals(2, integerDynArray.getItem(1).intValue());
        assertEquals(3, integerDynArray.getItem(2).intValue());
        assertEquals(4, integerDynArray.getItem(3).intValue());
    }

    @Test
    public void removeWhenCapacityOfArrayDecreases() {
        DynArray<Integer> integerDynArray = new DynArray<>(Integer.class);
        integerDynArray.appendMany(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
        assertEquals(16, integerDynArray.count);
        assertEquals(16, integerDynArray.capacity);
        integerDynArray.remove(0); // 1 - 15
        assertTrue(integerDynArray.possibleNewCapacity < integerDynArray.count);
        integerDynArray.remove(0); // 2 - 14
        integerDynArray.remove(0); // 3 - 13
        assertTrue(integerDynArray.possibleNewCapacity < integerDynArray.count);
        integerDynArray.remove(0); // 4 - 12
        integerDynArray.remove(0); // 5 - 11

        integerDynArray.remove(0); // 6 - 10: After that the capacity of the array is reduced to 10;
        assertFalse(integerDynArray.possibleNewCapacity < integerDynArray.count);
        assertEquals(10, integerDynArray.count);
        assertEquals(10, integerDynArray.capacity);

        assertEquals(6, integerDynArray.getItem(0).intValue());
        assertEquals(15, integerDynArray.getItem(9).intValue());
    }

}