package com.github.tyurinden;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Integer> integerList = new ArrayList<>();
        integerList.add(123);
        integerList.add(456);
        summ(integerList);

        List<Float> floatList = new ArrayList<>();
        floatList.add(1.1f);
        floatList.add(2.2f);
        summ(floatList);
    }

//    public static void summ(List<? super Number> numbers) {
    public static void summ(List<? extends Number> numbers) {
        List<Integer> integerList = (List<Integer>) numbers;
//        numbers.add(12);
        System.out.println(numbers);
    }
}
