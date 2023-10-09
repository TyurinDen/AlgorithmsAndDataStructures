package com.github.tyurinden.algorithms;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Инвертировать слова в заданной строке. 'aa bb ccc' -> 'ccc bb aa'. Строка может содержать пробелы в любом месте
 * <p>
 * Сложность: O((n)), где n - количество слов в строке
 **/
public class ReverseWords {
    public static void main(String[] args) {
        String input = "   a aa aaa     bbb";
        String[] clovenInput = input.trim().split(" ");

        for (String s : clovenInput) {
            if (s.equals("")) {
                System.out.println("s1 = empty string");
            } else if (s.equals(" ")) {
                System.out.println("s1 = space");
            } else {
                System.out.println("s1 = " + s);
            }
        }

        System.out.println(reverseWords(input));
    }

    private static String reverseWords(String s) {
        LinkedList<String> list = new LinkedList<>();

        Arrays.stream(s.trim().split(" "))
              .map(String::trim)
              .filter(str -> !str.equals(""))
              .forEach(list::push);

        return String.join(" ", list);
    }
}
