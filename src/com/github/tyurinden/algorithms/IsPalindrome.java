package com.github.tyurinden.algorithms;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * Определить является ли строка пониндромом. Пустая строка полиндромом считается
 * <p>
 * Сложность: O(n), где n - количество символов в строке
 **/
public class IsPalindrome {
    public static void main(String[] args) {
        var s1 = "A man, a plan, a canal: Panama";
        System.out.println("isPalindrome(s) = " + isPalindromeWithLL(s1));

        var s2 = "race a car";
        System.out.println("isPalindrome(s) = " + isPalindromeWithLL(s2));

        System.out.println("isPalindrome(s) = " + isPalindromeWithPtr(s1));

        System.out.println("isPalindrome(s) = " + isPalindromeWithPtr(s2));
    }

    public static boolean isPalindromeWithLL(String s) {
        // не самое эффективное решение по оценке литкод ни по памяти, ни по процу
        // надо делать двумя указателями
        if (s == null) {
            throw new IllegalArgumentException("Input string cannot be null");
        }
        if (s.isEmpty()) {
            return true;
        }

        HashSet<String> validChars = new HashSet<>(Arrays.asList("abcdefghijklmnopqrstuvwxyz1234567890".split("")));

        LinkedList<String> reversed = new LinkedList<>();
        LinkedList<String> directed = new LinkedList<>();

        String[] splitString = s.trim().toLowerCase().split("");

        Arrays.stream(splitString)
              .filter(validChars::contains)
              .forEach(directed::addLast);

        Arrays.stream(splitString)
              .filter(validChars::contains)
              .forEach(reversed::push);

        String reversedString = String.join("", reversed);
        String directedString = String.join("", directed);

        //        System.out.println("reversedString = " + reversedString);
        //        System.out.println("directedString = " + directedString);

        return directedString.equals(reversedString);
    }

    public static boolean isPalindromeWithPtr(String s) {
        if (s == null) {
            return false;
        }
        if (s.isEmpty()) {
            return true;
        }

        s = s.toLowerCase().replaceAll("[^a-z0-9]", "");

        //todo: вместо regex, он работает сильно медленнее чем код ниже
//        StringBuilder b = new StringBuilder();
//        for(int x = 0; x < s.length(); x++){
//            if(Character.isLetterOrDigit(s.charAt(x))){
//                b.append(s.charAt(x));
//            }
//        }

        int i = 0;
        int j = s.length() - 1;

        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }

        return true;
    }
}
