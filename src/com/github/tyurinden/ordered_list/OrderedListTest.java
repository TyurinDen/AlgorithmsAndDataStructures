package com.github.tyurinden.ordered_list;

import org.junit.Test;

import static org.junit.Assert.*;

public class OrderedListTest {

    private OrderedList<Integer> createAscIntOrderedList(final int... ints) {
        final OrderedList<Integer> orderedList = new OrderedList<>(true);
        for (final int anInt : ints) {
            orderedList.add(anInt);
        }
        return orderedList;
    }

    private OrderedList<Integer> createDscIntOrderedList(final int... ints) {
        final OrderedList<Integer> orderedList = new OrderedList<>(false);
        for (final int anInt : ints) {
            orderedList.add(anInt);
        }
        return orderedList;
    }

    @Test
    public void compare() {
        final OrderedList<Integer> intOrderedList = new OrderedList<>(true);
        assertEquals(-1, intOrderedList.compare(1, 2));
        assertEquals(0, intOrderedList.compare(2, 2));
        assertEquals(1, intOrderedList.compare(3, 2));

        final OrderedList<String> strOrderedList = new OrderedList<>(true);
        assertEquals(-1, strOrderedList.compare("aa", "ab"));
        assertEquals(0, strOrderedList.compare("aaa", "aaa"));
        assertEquals(1, strOrderedList.compare("ab", "aa"));

        assertEquals(-1, strOrderedList.compare("aa", "aaa"));
        assertEquals(0, strOrderedList.compare("aaa", "aaa"));
        assertEquals(1, strOrderedList.compare("aaa", "aa"));
    }

    @Test
    public void add() {
        final OrderedList<Integer> orderedList = createAscIntOrderedList(1, 2, 4, 2, 3, 3, 4, 2, 6);
        assertEquals(9, orderedList.count());
    }

    @Test
    public void findInAscList() {
        final OrderedList<Integer> orderedList = createAscIntOrderedList(1, 2, 4, 2, 3, 3, 4, 2, 6);

        assertEquals(Integer.valueOf(2), orderedList.getNodeValue(orderedList.find(2)));

        assertEquals(Integer.valueOf(3), orderedList.getNodeValue(orderedList.find(3)));

        assertEquals(Integer.valueOf(4), orderedList.getNodeValue(orderedList.find(4)));

        assertNull(orderedList.find(-1));
        assertNull(orderedList.find(100));
    }

    @Test
    public void findInDscList() {
        final OrderedList<Integer> orderedList = createAscIntOrderedList(1, 2, 3);

        assertEquals(Integer.valueOf(2), orderedList.getNodeValue(orderedList.find(2)));

        assertEquals(Integer.valueOf(1), orderedList.getNodeValue(orderedList.find(1)));

        assertEquals(Integer.valueOf(3), orderedList.getNodeValue(orderedList.find(3)));

        assertNull(orderedList.find(-1));
        assertNull(orderedList.find(100));
    }

    @Test
    public void delete() {
        final OrderedList<Integer> orderedList = createAscIntOrderedList(1, 2, 4, 2, 3, 3, 4, 2, 6);
//        System.out.println(orderedList.toString());
        orderedList.delete(2);
        assertNull(orderedList.find(2));
        assertEquals(6, orderedList.count());

        assertNotNull(orderedList.find(4));
        orderedList.delete(4);
        assertNull(orderedList.find(4));
        assertEquals(4, orderedList.count());

        assertNotNull(orderedList.find(3));
        orderedList.delete(3);
        assertNull(orderedList.find(3));
        assertEquals(2, orderedList.count());
    }

    @Test
    public void clear() {
        final OrderedList<Integer> orderedList = new OrderedList<>(true);
        orderedList.add(1);
        orderedList.add(2);
        orderedList.add(3);
        assertEquals(3, orderedList.count());
        orderedList.clear(true);
        assertEquals(0, orderedList.count());
    }

    @Test
    public void complexCheckOrderedAscList() {
        final OrderedList<Integer> orderedList = createAscIntOrderedList(3, 1, 2, 0, 10, 8, 4, 7, 9, 5, 6, -2, -1);
//        System.out.println(orderedList);
        for (int i = -2; i < 11; i++) {
            assertEquals(Integer.valueOf(i), orderedList.removeFront());
        }
    }

    @Test
    public void complexCheckOrderedDscList() {
        final OrderedList<Integer> orderedList = createDscIntOrderedList(-1, -2, 3, 1, 2, 0, 10, 8, 4, 7, 9, 5, 6);
//        System.out.println(orderedList);
        for (int i = 10; i >= -2; i--) {
            assertEquals(Integer.valueOf(i), orderedList.removeFront());
        }
    }

}