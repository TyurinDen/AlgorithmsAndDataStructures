package com.github.tyurinden.ordered_list;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class OrderedListTest {

    private OrderedList<Integer> createAscIntOrderedList(final int... ints) {
        final OrderedList<Integer> orderedList = new OrderedList<>(true);
        for (final int anInt : ints) {
            orderedList.add(anInt);
        }
        return orderedList;
    }

    private OrderedList<Double> createAscDoubleOrderedList(final double... doubles) {
        final OrderedList<Double> orderedList = new OrderedList<>(true);
        for (final double d : doubles) {
            orderedList.add(d);
        }
        return orderedList;
    }

    private OrderedList<Float> createAscDoubleOrderedList(final float... floats) {
        final OrderedList<Float> orderedList = new OrderedList<>(true);
        for (final float f : floats) {
            orderedList.add(f);
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

    private OrderedList<String> createAscStringOrderedList(final String... strings) {
        final OrderedList<String> orderedList = new OrderedList<>(true);
        for (final String s : strings) {
            orderedList.add(s);
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
    public void complexCheckOrderedIntAscList() {
        final OrderedList<Integer> orderedList = createAscIntOrderedList(3, 1, 2, 0, 10, 8, 4, 7, 9, 5, 6, -2, -1, 11);
//        System.out.println(orderedList);
        for (int i = -2; i < 12; i++) {
            assertEquals(Integer.valueOf(i), orderedList.removeFront());
        }
    }

    @Test
    public void whenFindAll_ThenReturnArrayList() {
        final OrderedList<Integer> orderedList = createAscIntOrderedList(3, 1, 2, 0, 10, 8, 4, 7, 9, 5, 6, -2, -1, 11);
        final List<Integer> integerListExpected = List.of(-2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
        for (int i = 0; i < orderedList.getAll().size(); i++) {
            assertEquals(integerListExpected.get(i), orderedList.getAll().get(i).getValue());
        }
    }

    @Test
    public void complexCheckOrderedDoubleAscList() {
        final OrderedList<Double> orderedList = createAscDoubleOrderedList(
                3.1d,
                1.3,
                2f,
                0.3f,
                10.2f,
                8.1f,
                4f,
                7.3f,
                9.1,
                5.9,
                6.5f,
                -2.1f,
                -1.7f
        );
//        System.out.println(orderedList);
        Double f1 = orderedList.removeFront();
        while (f1 != null) {
            final Double f2 = orderedList.removeFront();
            if (f2 != null) {
                assertTrue(f1 < f2);
            }
            f1 = f2;
        }
    }

    @Test
    public void complexCheckOrderedFloatAscList() {
        final OrderedList<Float> orderedList = createAscDoubleOrderedList(
                3.1f,
                1.3f,
                2f,
                0.3f,
                10.2f,
                8.1f,
                4f,
                7.3f,
                9.1f,
                5.9f,
                6.5f,
                -2.1f,
                -1.7f
        );
//        System.out.println(orderedList);
        Float f1 = orderedList.removeFront();
        while (f1 != null) {
            Float f2 = orderedList.removeFront();
            if (f2 != null) {
                assertTrue(f1 < f2);
            }
            f1 = f2;
        }
    }

    @Test
    public void complexCheckOrderedIntDscList() {
        final OrderedList<Integer> orderedList = createDscIntOrderedList(4, 6, 3, 1, 2, 0, 10, 8, -1, 7, 9, 5, -2);
//        System.out.println(orderedList);
        for (int i = 10; i >= -2; i--) {
            assertEquals(Integer.valueOf(i), orderedList.removeFront());
        }
    }

}