package com.github.tyurinden.algorithms;

import java.util.Arrays;

/**
 * Дан массив целых чисел, представляющий собой число [1,4,5,2] -> 1452. Увеличить это число на единицу и вернуть в виде массива
 * Пример: [1,4,5,2] + 1 == [1,4,5,3]; [1,4,9] + 1 == [1,5,0]; [9,9,9] + 1 == [1,0,0,0]
 * <p>
 * Сложность: O(n), где n - количество элементов в массиве
 **/
public class PlusOne {
    public static void main(String[] args) {
        int[] plusOne = plusOne(new int[] {1, 2, 3, 4});
        System.out.println("plusOne = " + Arrays.toString(plusOne));

        plusOne = plusOne(new int[] {1, 2, 3, 9});
        System.out.println("plusOne = " + Arrays.toString(plusOne));

        plusOne = plusOne(new int[] {1, 2, 9, 1});
        System.out.println("plusOne = " + Arrays.toString(plusOne));

        plusOne = plusOne(new int[] {1, 9, 9, 9});
        System.out.println("plusOne = " + Arrays.toString(plusOne));

        plusOne = plusOne(new int[] {8, 9, 9, 9, 9});
        System.out.println("plusOne = " + Arrays.toString(plusOne));

        plusOne = plusOne(new int[] {9, 9, 9});
        System.out.println("plusOne = " + Arrays.toString(plusOne));

        plusOne = plusOne(new int[] {9, 9, 9, 9, 9});
        System.out.println("plusOne = " + Arrays.toString(plusOne));
    }

    //todo: хорошее решеие по литкод: 0ms Beats 100.00%of users with Java, 40.64MB Beats 80.78%of users with Java
    private static int[] plusOne(int[] digits) {
        if (digits[0] == 0) {
            return new int[] {1};
        }

        int i = digits.length - 1;
        while (i > -1 && digits[i] == 9) {
            digits[i] = 0;
            i--;
        }

        if (i < 0) {
            digits = new int[digits.length + 1];
            digits[0] = 1;
            return digits;
        }
        digits[i]++;

        return digits;
    }
}
