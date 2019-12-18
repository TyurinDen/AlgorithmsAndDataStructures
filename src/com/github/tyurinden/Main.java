package com.github.tyurinden;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws CloneNotSupportedException, InterruptedException {
        Qux qux = new Qux(1);
        Qux.Corge corge = qux.new Corge();
        Qux.Quux quux = new Qux.Quux();
        long l = Long.MIN_VALUE;
        while (true) {
            String s = String.valueOf(l).intern();
            l++;
        }
    }

//    public static void summ(List<? super Number> numbers) {
    public static void summ(List<? extends Number> numbers) {
        List<Integer> integerList = (List<Integer>) numbers;
//        numbers.add(12);
        System.out.println(numbers);
    }
}
