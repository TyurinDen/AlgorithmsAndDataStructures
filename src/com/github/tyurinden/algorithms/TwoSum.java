package com.github.tyurinden.algorithms;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * В массиве целых чисел найти индексы двух элементов, которые в сумме дают заданное число. Вернуть массив с индексами этих элементов
 * Считаем, что в массиве нет повторяющихся элементов
 * [2, 7, 11, 15], target 9. result: [0, 1]
 * <p>
 * Сложность: O((n)), где n - количество элементов в массиве
 **/
public class TwoSum {
    public static void main(String[] args) {
        var result1 = twoSum(new int[] {1, 7, 11, 15, 2}, 9);
        System.out.println("result = " + Arrays.toString(result1));

        var result2 = twoSum(new int[] {1, 4, 11, 15, 2}, 8);
        System.out.println("result = " + Arrays.toString(result2));

        var result3 = twoSum(new int[] {1, 4, 11, 15, 2}, 0);
        System.out.println("result = " + Arrays.toString(result3));
    }

    private static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> numsMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            numsMap.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            if (numsMap.containsKey(target - nums[i]) && numsMap.get(target - nums[i]) != i) {
                return new int[] {i, numsMap.get(target - nums[i])};
            }
        }

        return new int[0];
    }
}
