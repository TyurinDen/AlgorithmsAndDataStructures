package com.github.tyurinden.dyn_array;

import java.lang.reflect.Array;
import java.util.Arrays;

public class DynArray<T> {
    public T[] array;
    public int count;
    public int capacity;
    Class clazz;

    public DynArray(Class clz) {
        clazz = clz; // нужен для безопасного приведения типов
        count = 0;
        capacity = 16;
        array = (T[]) Array.newInstance(this.clazz, 16);
    }

    public DynArray(Class clz, int initialCapacity) {
        clazz = clz;
        count = 0;
        capacity = initialCapacity;
        array = (T[]) Array.newInstance(this.clazz, capacity);
    }

    public void makeArray(int new_capacity) {
        this.capacity = new_capacity;
        array = Arrays.copyOf(array, new_capacity);
    }

    public T getItem(int index) {
        if (index < 0 || index > this.count - 1) {
            throw new IllegalArgumentException("Index is out of bounds!");
        }
        return array[index];
    }

    public void append(T itm) {
        if (capacity < count + 1) {
            makeArray(capacity * 2);
        }
        array[count++] = itm;
    }

    public void appendMany(T... itm) {
        if (capacity < itm.length + count) {
            int newCapacity = ((itm.length + count + 1) / capacity + 1) * capacity;
            makeArray(newCapacity);
        }
        int indexTo = itm.length + count;
        for (int j = 0; count < indexTo; j++) {
            array[count++] = itm[j];
        }
    }

    public void insert(T itm, int index) {
        if (index < 0 || index > this.count - 1) {
            throw new IllegalArgumentException("Index is out of bounds!");
        }
        if (capacity < count + 1) {
            makeArray(capacity * 2);
        }
        if (count - index >= 0) {
            System.arraycopy(array, index, array, index + 1, count - index);
        }
        array[index] = itm;
        count++;
    }

    public void remove(int index) {
        // ваш код
    }

}
