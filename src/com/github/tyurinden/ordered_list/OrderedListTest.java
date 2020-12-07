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
        assertEquals(-1, intOrderedList.compare(1, 5));
        assertEquals(0, intOrderedList.compare(2, 2));
        assertEquals(1, intOrderedList.compare(5, 1));

        final OrderedList<String> strOrderedList = createAscStringOrderedList(
                "aaa",
                "ab",
                "aa",
                "aa",
                "ba",
                "aaa",
                "bb",
                "bb",
                "aaaa",
                "aaaa",
                "bbb"
        );
        assertEquals("{ aa, aa, aaa, aaa, aaaa, aaaa, ab, ba, bb, bb, bbb }", strOrderedList.toString());
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
        final OrderedList<Integer> orderedList = createDscIntOrderedList(1, 2, 3);

        assertEquals(Integer.valueOf(2), orderedList.getNodeValue(orderedList.find(2)));

        assertEquals(Integer.valueOf(1), orderedList.getNodeValue(orderedList.find(1)));

        assertEquals(Integer.valueOf(3), orderedList.getNodeValue(orderedList.find(3)));

        assertNull(orderedList.find(-1));
        assertNull(orderedList.find(100));
    }

    @Test(expected = NullPointerException.class)
    public void equalsTest() {
        OrderedList<Integer> orderedList1 = createAscIntOrderedList(3, 1, 2, 0, 10, 8, 4, 7, 9, 5, 6, -2, -1, 11);
        OrderedList<Integer> orderedList2 = createAscIntOrderedList(3, 5, 2, 0, 11, 8, 4, 9, 7, 1, 6, -2, -1, 10);
        OrderedList<String> stringList = createAscStringOrderedList("aa", "aaa", "ab", "bbb");

        assertEquals(orderedList1, orderedList1);
        assertEquals(orderedList1, orderedList2);
        assertEquals(stringList, stringList);
        assertNotEquals(orderedList1, stringList);

        orderedList1.deleteAll(0);
        orderedList2.deleteAll(1);
        assertNotEquals(orderedList1, orderedList2);

        orderedList1.add(0);
        orderedList2.add(1);
        assertEquals(orderedList1, orderedList2);

        orderedList1.deleteAll(0);
        assertNotEquals(orderedList1, orderedList2);

        orderedList1.clear(true);
        assertNotEquals(orderedList1, orderedList2);

        orderedList2.clear(true);
        assertEquals(orderedList1, orderedList2);

        orderedList1.add(1);
        orderedList1.add(2);

        orderedList2.add(2);
        orderedList2.add(1);
        assertEquals(orderedList1, orderedList2);

        orderedList2 = null;
        assertNotEquals(orderedList1, orderedList2);

        orderedList1 = null;
        orderedList1.equals(orderedList2); // NPE
    }

    @Test
    public void delete() {
        final OrderedList<Integer> orderedList = createAscIntOrderedList(1, 2, 4, 2, 3, 3, 4, 2, 6);

        orderedList.delete(2);
        assertEquals(8, orderedList.count());
        OrderedList<Integer> orderedListExpected = createAscIntOrderedList(1, 2, 2, 3, 3, 4, 4, 6);
        assertEquals(orderedListExpected, orderedList);

        orderedList.delete(6);
        orderedListExpected = createAscIntOrderedList(1, 2, 2, 3, 3, 4, 4);
        assertEquals(orderedListExpected, orderedList);
    }

    @Test
    public void deleteAll() {
        final OrderedList<Integer> orderedList = createAscIntOrderedList(1, 2, 4, 2, 3, 3, 4, 2, 6);
        OrderedList<Integer> orderedListExpected = createAscIntOrderedList(1, 2, 2, 2, 3, 3, 4, 4, 6);
        assertEquals(orderedListExpected, orderedList);

        orderedList.deleteAll(2);
        assertNull(orderedList.find(2));
        assertEquals(6, orderedList.count());

        orderedListExpected = createAscIntOrderedList(1, 3, 3, 4, 4, 6);
        assertEquals(orderedList, orderedListExpected);

        assertNotNull(orderedList.find(4));
        orderedList.deleteAll(4);
        assertNull(orderedList.find(4));
        assertEquals(4, orderedList.count());

        orderedListExpected = createAscIntOrderedList(1, 3, 3, 6);
        assertEquals(orderedList, orderedListExpected);

        assertNotNull(orderedList.find(3));
        orderedList.deleteAll(3);
        assertNull(orderedList.find(3));
        assertEquals(2, orderedList.count());

        orderedListExpected = createAscIntOrderedList(1, 6);
        assertEquals(orderedList, orderedListExpected);


        assertNotNull(orderedList.find(6));
        orderedList.deleteAll(6);
        assertNull(orderedList.find(6));
        assertEquals(1, orderedList.count());

        orderedListExpected = createAscIntOrderedList(1);
        assertEquals(orderedList, orderedListExpected);


        orderedList.add(2);
        orderedList.add(3);
        assertNotNull(orderedList.find(1));

        orderedListExpected = createAscIntOrderedList(1, 2, 3);
        assertEquals(orderedList, orderedListExpected);

        orderedList.deleteAll(1);
        assertNull(orderedList.find(1));
        assertEquals(2, orderedList.count());

        orderedList.deleteAll(2);
        orderedListExpected = createAscIntOrderedList(3);
        assertEquals(orderedList, orderedListExpected);

        orderedList.deleteAll(2);
        orderedListExpected = createAscIntOrderedList(3);
        assertEquals(orderedList, orderedListExpected);

        orderedList.deleteAll(3);
        orderedListExpected = createAscIntOrderedList();
        assertEquals(orderedList, orderedListExpected);
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