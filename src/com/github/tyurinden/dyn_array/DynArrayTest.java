package com.github.tyurinden.dyn_array;

import org.junit.Assert;
import org.junit.Test;

public class DynArrayTest {

    @Test
    public void makeArrayWithStringType() {
        DynArray<String> integerDynArray = new DynArray<>(String.class);
        Assert.assertEquals(16, integerDynArray.capacity);
        Assert.assertEquals(0, integerDynArray.count);
        integerDynArray.appendMany("1", "2", "3");
        Assert.assertEquals(3, integerDynArray.count);
        Assert.assertEquals(16, integerDynArray.capacity);
        Assert.assertEquals("1", integerDynArray.getItem(0));
        Assert.assertEquals("2", integerDynArray.getItem(1));
        Assert.assertEquals("3", integerDynArray.getItem(2));
    }

    @Test
    public void makeArray() {
        DynArray<Integer> integerDynArray = new DynArray<>(Integer.class);
        Assert.assertEquals(16, integerDynArray.capacity);
        Assert.assertEquals(0, integerDynArray.count);
        integerDynArray.appendMany(1, 2, 3);
        Assert.assertEquals(3, integerDynArray.count);
        Assert.assertEquals(16, integerDynArray.capacity);
        Assert.assertEquals(1, integerDynArray.getItem(0).intValue());
        Assert.assertEquals(2, integerDynArray.getItem(1).intValue());
        Assert.assertEquals(3, integerDynArray.getItem(2).intValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void makeArrayWhenCapacityOfArrayIncreases() {
        DynArray<Integer> integerDynArray = new DynArray<>(Integer.class);
        integerDynArray.appendMany(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
        Assert.assertEquals(16, integerDynArray.count);
        integerDynArray.append(16);
        Assert.assertEquals(17, integerDynArray.count);
        Assert.assertEquals(32, integerDynArray.capacity);
        Assert.assertEquals(0, integerDynArray.getItem(0).intValue());
        Assert.assertEquals(15, integerDynArray.getItem(15).intValue());
        Assert.assertEquals(16, integerDynArray.getItem(16).intValue());
        integerDynArray.getItem(integerDynArray.count); // throws IllegalArgumentException
    }

    @Test(expected = IllegalArgumentException.class)
    public void insert() {
        DynArray<Integer> integerDynArray = new DynArray<>(Integer.class);
        integerDynArray.appendMany(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        Assert.assertEquals(16, integerDynArray.capacity);
        integerDynArray.insert(44, 4);
        Assert.assertEquals(11, integerDynArray.count);
        Assert.assertEquals(0, integerDynArray.getItem(0).intValue());
        Assert.assertEquals(1, integerDynArray.getItem(1).intValue());
        Assert.assertEquals(2, integerDynArray.getItem(2).intValue());
        Assert.assertEquals(3, integerDynArray.getItem(3).intValue());
        Assert.assertEquals(44, integerDynArray.getItem(4).intValue());
        Assert.assertEquals(4, integerDynArray.getItem(5).intValue());
        Assert.assertEquals(5, integerDynArray.getItem(6).intValue());
        Assert.assertEquals(6, integerDynArray.getItem(7).intValue());
        Assert.assertEquals(7, integerDynArray.getItem(8).intValue());
        Assert.assertEquals(8, integerDynArray.getItem(9).intValue());
        Assert.assertEquals(9, integerDynArray.getItem(10).intValue());
        integerDynArray.getItem(integerDynArray.count); // throws IllegalArgumentException
    }

    @Test
    public void insertWhenCapacityOfArrayIncreases() {
        DynArray<Integer> integerDynArray = new DynArray<>(Integer.class);
        for (int i = 0; i < 1048; i++) {
            integerDynArray.append(i);
        }
        Assert.assertEquals(1048, integerDynArray.count);
        Assert.assertEquals(2048, integerDynArray.capacity);

        integerDynArray.remove(integerDynArray.count - 1);
        Assert.assertEquals(1047, integerDynArray.count);
        Assert.assertEquals(2048, integerDynArray.capacity);

        for (int i = 0; i < 23; i++) {
            integerDynArray.remove(integerDynArray.count - 1);
        }
        Assert.assertEquals(1024, integerDynArray.count);
        Assert.assertEquals(2048, integerDynArray.capacity);

        integerDynArray.remove(integerDynArray.count - 1);
        Assert.assertEquals(1023, integerDynArray.count);
        Assert.assertEquals(1365, integerDynArray.capacity);
    }

    @Test(expected = IllegalArgumentException.class)
    public void insertWhenArrayIsFull() {
        DynArray<Integer> integerDynArray = new DynArray<>(Integer.class, 5);
        integerDynArray.appendMany(0, 1, 2, 3, 4);
        Assert.assertEquals(5, integerDynArray.count);
        Assert.assertEquals(5, integerDynArray.capacity);
        integerDynArray.insert(44, integerDynArray.count);
        Assert.assertEquals(10, integerDynArray.capacity);
        Assert.assertEquals(6, integerDynArray.count);
        Assert.assertEquals(44, integerDynArray.getItem(6).intValue());
        Assert.assertEquals(0, integerDynArray.getItem(0).intValue());
        integerDynArray.getItem(6); // throws IllegalArgumentException
    }

    @Test
    public void insertWhenArrayIsEmpty() {
        DynArray<Integer> integerDynArray = new DynArray<>(Integer.class);
        Assert.assertEquals(0, integerDynArray.count);
        Assert.assertEquals(16, integerDynArray.capacity);
        integerDynArray.insert(22, 0);
        Assert.assertEquals(1, integerDynArray.count);
        Assert.assertEquals(22, integerDynArray.getItem(0).intValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void insertWhenIndexIsIncorrect() {
        DynArray<Integer> integerDynArray = DynArray.createIntegerDynArrayAndPopulateItWithTestData(20);
        Assert.assertEquals(20, integerDynArray.count);
        Assert.assertEquals(20, integerDynArray.capacity);
        integerDynArray.insert(22, 20);
        integerDynArray.insert(22, -1);
    }

    @Test
    public void remove() {
        DynArray<Integer> integerDynArray = new DynArray<>(Integer.class);
        integerDynArray.appendMany(0, 1, 2, 3, 4);
        Assert.assertEquals(5, integerDynArray.count);
        Assert.assertEquals(16, integerDynArray.capacity);
        integerDynArray.remove(2);
        Assert.assertEquals(4, integerDynArray.count);
        Assert.assertEquals(16, integerDynArray.capacity);
        Assert.assertEquals(0, integerDynArray.getItem(0).intValue());
        Assert.assertEquals(1, integerDynArray.getItem(1).intValue());
        Assert.assertEquals(3, integerDynArray.getItem(2).intValue());
        Assert.assertEquals(4, integerDynArray.getItem(3).intValue());
    }

    @Test
    public void appendAndRemoveManyManyElementsFromEndOfArray() {
        DynArray<Integer> integerDynArray = new DynArray<>(Integer.class);
        for (int i = 0; i < 100_000; i++) {
            integerDynArray.append(i);
        }
        Assert.assertEquals(100_000, integerDynArray.count);
//      capacity = 131_072
        Assert.assertEquals(0, integerDynArray.getItem(0).intValue());
        Assert.assertEquals(99_999, integerDynArray.getItem(99_999).intValue());

        for (int i = 0; i < 99_999; i++) {
            integerDynArray.remove(integerDynArray.count - 1);
            Assert.assertEquals(integerDynArray.count - 1,
                    integerDynArray.getItem(integerDynArray.count - 1).intValue());
        }

        Assert.assertEquals(1, integerDynArray.count);
        Assert.assertEquals(16, integerDynArray.capacity);

        integerDynArray.remove(0);
        Assert.assertEquals(0, integerDynArray.count);
        Assert.assertEquals(16, integerDynArray.capacity);
    }

    @Test
    public void appendAndRemoveManyManyElementsFromBeginningOfArray() {
        DynArray<Integer> integerDynArray = new DynArray<>(Integer.class);
        for (int i = 0; i < 100_000; i++) {
            integerDynArray.append(i);
        }
        Assert.assertEquals(100_000, integerDynArray.count);
        Assert.assertEquals(0, integerDynArray.getItem(0).intValue());
        Assert.assertEquals(99_999, integerDynArray.getItem(99_999).intValue());

        for (int i = 0; i < 99_999; i++) {
            integerDynArray.remove(0);
            Assert.assertEquals(i + 1, integerDynArray.getItem(0).intValue());
        }
        Assert.assertEquals(1, integerDynArray.count);
        Assert.assertEquals(16, integerDynArray.capacity);

        integerDynArray.remove(0);
        Assert.assertEquals(0, integerDynArray.count);
        Assert.assertEquals(16, integerDynArray.capacity);
    }

    @Test
    public void appendAndRemoveManyManyElementsInRandomOrder() {
        DynArray<Integer> integerDynArray = new DynArray<>(Integer.class);
        int insIndex;
        for (int i = 0; i < 10_000; i++) {
            insIndex = (int) ((Math.random() * integerDynArray.count));
            integerDynArray.insert(i, insIndex);
        }
        Assert.assertEquals(10_000, integerDynArray.count);

        int delIndex;
        for (int i = 0; i < 9_999; i++) {
            delIndex = (int) ((Math.random() * integerDynArray.count) - 1);
            integerDynArray.remove(delIndex);
        }
        Assert.assertEquals(1, integerDynArray.count);
        Assert.assertEquals(16, integerDynArray.capacity);
    }

    @Test
    public void appendAndRemoveManyManyElementsInRandomOrderWithChecking() {
        DynArray<Integer> integerDynArray = new DynArray<>(Integer.class);
        int insIndex;
        Assert.assertEquals(0, integerDynArray.count);
        for (int i = 0; i < 100; i++) {
            insIndex = (int) ((Math.random() * integerDynArray.count));
//            System.out.print(insIndex + " ");
            integerDynArray.insert(i, insIndex); // элементы уникальны, индексы произвольны
        }
        Assert.assertEquals(100, integerDynArray.count);
//        System.out.println(integerDynArray);

        for (int i = 0; i < 10; i++) {
            integerDynArray.remove(0);
        }
        Assert.assertEquals(90, integerDynArray.count);
//        System.out.println(integerDynArray);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeWhenRemovedItemIsLast() {
        DynArray<Integer> integerDynArray = new DynArray<>(Integer.class);
        integerDynArray.appendMany(0, 1, 2);
        Assert.assertEquals(3, integerDynArray.count);
        Assert.assertEquals(16, integerDynArray.capacity);
        integerDynArray.remove(integerDynArray.count - 1);
        Assert.assertEquals(2, integerDynArray.count);
        Assert.assertEquals(16, integerDynArray.capacity);
        Assert.assertEquals(0, integerDynArray.getItem(0).intValue());
        Assert.assertEquals(1, integerDynArray.getItem(1).intValue());

        integerDynArray.remove(integerDynArray.count - 1);
        Assert.assertEquals(1, integerDynArray.count);
        Assert.assertEquals(16, integerDynArray.capacity);
        Assert.assertEquals(0, integerDynArray.getItem(0).intValue());

        integerDynArray.remove(integerDynArray.count - 1);
        Assert.assertEquals(0, integerDynArray.count);
        Assert.assertEquals(16, integerDynArray.capacity);
        integerDynArray.remove(integerDynArray.count - 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeAllArrayItems() {
        DynArray<Integer> integerDynArray = new DynArray<>(Integer.class);
        integerDynArray.appendMany(0, 1, 2, 3, 4);
        Assert.assertEquals(5, integerDynArray.count);
        Assert.assertEquals(16, integerDynArray.capacity);
        integerDynArray.remove(2);
        Assert.assertEquals(4, integerDynArray.count);
        Assert.assertEquals(16, integerDynArray.capacity);
        Assert.assertEquals(0, integerDynArray.getItem(0).intValue());
        Assert.assertEquals(1, integerDynArray.getItem(1).intValue());
        Assert.assertEquals(3, integerDynArray.getItem(2).intValue());
        Assert.assertEquals(4, integerDynArray.getItem(3).intValue());

        integerDynArray.remove(2);
        Assert.assertEquals(3, integerDynArray.count);
        Assert.assertEquals(16, integerDynArray.capacity);
        Assert.assertEquals(0, integerDynArray.getItem(0).intValue());
        Assert.assertEquals(1, integerDynArray.getItem(1).intValue());
        Assert.assertEquals(4, integerDynArray.getItem(2).intValue());

        integerDynArray.remove(1);
        Assert.assertEquals(2, integerDynArray.count);
        Assert.assertEquals(16, integerDynArray.capacity);
        Assert.assertEquals(0, integerDynArray.getItem(0).intValue());
        Assert.assertEquals(4, integerDynArray.getItem(1).intValue());

        integerDynArray.remove(1);
        Assert.assertEquals(1, integerDynArray.count);
        Assert.assertEquals(16, integerDynArray.capacity);
        Assert.assertEquals(0, integerDynArray.getItem(0).intValue());

        integerDynArray.remove(0);
        Assert.assertEquals(0, integerDynArray.count);
        Assert.assertEquals(16, integerDynArray.capacity);
        integerDynArray.getItem(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeWhenRemovedItemIsFirst() {
        DynArray<Integer> integerDynArray = new DynArray<>(Integer.class);
        integerDynArray.appendMany(0, 1, 2);
        Assert.assertEquals(3, integerDynArray.count);
        Assert.assertEquals(16, integerDynArray.capacity);
        integerDynArray.remove(0);
        Assert.assertEquals(2, integerDynArray.count);
        Assert.assertEquals(16, integerDynArray.capacity);
        Assert.assertEquals(1, integerDynArray.getItem(0).intValue());
        Assert.assertEquals(2, integerDynArray.getItem(1).intValue());

        integerDynArray.remove(0);
        Assert.assertEquals(1, integerDynArray.count);
        Assert.assertEquals(2, integerDynArray.getItem(0).intValue());

        integerDynArray.remove(0);
        Assert.assertEquals(0, integerDynArray.count);
        integerDynArray.remove(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeWhenIsOnlyOneElementInArray() {
        DynArray<Integer> integerDynArray = new DynArray<>(Integer.class);
        integerDynArray.append(0);
        Assert.assertEquals(1, integerDynArray.count);
        Assert.assertEquals(16, integerDynArray.capacity);
        integerDynArray.remove(0);
        Assert.assertEquals(0, integerDynArray.count);
        Assert.assertEquals(16, integerDynArray.capacity);
        integerDynArray.remove(0);
    }

    @Test // тест проверял функционал уменьшения емкости массива без ограничения снизу. с ограничением не проходит
    public void removeWhenCapacityOfArrayDecreases() {
        DynArray<Integer> integerDynArray = new DynArray<>(Integer.class);
        integerDynArray.appendMany(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
        Assert.assertEquals(16, integerDynArray.count);
        Assert.assertEquals(16, integerDynArray.capacity);
        integerDynArray.remove(0); // 1 - 15
        Assert.assertTrue(integerDynArray.possibleNewCapacity < integerDynArray.count);
        integerDynArray.remove(0); // 2 - 14
        integerDynArray.remove(0); // 3 - 13
        Assert.assertTrue(integerDynArray.possibleNewCapacity < integerDynArray.count);
        integerDynArray.remove(0); // 4 - 12
        integerDynArray.remove(0); // 5 - 11

        integerDynArray.remove(0); // 6 - 10: After that the capacity of the array is reduced to 10;
        Assert.assertFalse(integerDynArray.possibleNewCapacity < integerDynArray.count);
        Assert.assertEquals(10, integerDynArray.count);
        Assert.assertEquals(10, integerDynArray.capacity);

        Assert.assertEquals(6, integerDynArray.getItem(0).intValue());
        Assert.assertEquals(15, integerDynArray.getItem(9).intValue());
    }

    @Test
    public void removeWhenCapacityOfArrayDecreases2() {
        DynArray<Integer> integerDynArray = DynArray.createIntegerDynArrayAndPopulateItWithTestData(50);
        Assert.assertEquals(50, integerDynArray.count);
        Assert.assertEquals(50, integerDynArray.capacity);
        for (int i = 0; i < 25; i++) {
            integerDynArray.remove(integerDynArray.count - 1);
        }
        Assert.assertEquals(25, integerDynArray.count);
        Assert.assertEquals(50, integerDynArray.capacity);

        integerDynArray.remove(integerDynArray.count - 1);
        Assert.assertEquals(24, integerDynArray.count);
        Assert.assertEquals(33, integerDynArray.capacity);
    }

    @Test
    public void removeWhenCapacityOfArrayDecreases3() {
        DynArray<Integer> integerDynArray = DynArray.createIntegerDynArrayAndPopulateItWithTestData(20);
        Assert.assertEquals(20, integerDynArray.count);
        Assert.assertEquals(20, integerDynArray.capacity);
        for (int i = 0; i < 10; i++) {
            integerDynArray.remove(0);
        }
        Assert.assertEquals(10, integerDynArray.count);
        Assert.assertEquals(20, integerDynArray.capacity);

        integerDynArray.remove(0);
        Assert.assertEquals(9, integerDynArray.count);
        Assert.assertEquals(16, integerDynArray.capacity);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeWhenIndexIsIncorrect() {
        DynArray<Integer> integerDynArray = new DynArray<>(Integer.class);
        integerDynArray.appendMany(0, 1, 2, 3, 4);
        Assert.assertEquals(5, integerDynArray.count);
        Assert.assertEquals(16, integerDynArray.capacity);
        integerDynArray.remove(5);
        integerDynArray.remove(-1);
    }

    @Test
    public void populateArrayWithTestIntegerData() {
        DynArray<Integer> integerDynArray = DynArray.createIntegerDynArrayAndPopulateItWithTestData(50);
        Assert.assertEquals(50, integerDynArray.count);
        Assert.assertEquals(50, integerDynArray.capacity);
        Assert.assertEquals(0, integerDynArray.getItem(0).intValue());
        Assert.assertEquals(49, integerDynArray.getItem(49).intValue());
    }
}
