package com.github.tyurinden.algorithms;

public class LengthOfLongestSubstring {
    public static void main(String[] args) {
        System.out.println(getLengthOfLongestSubstring("abcabcbb"));
        System.out.println(getLengthOfLongestSubstring("bbbbb"));
        System.out.println(getLengthOfLongestSubstring("pwwkew"));
        System.out.println(getLengthOfLongestSubstring("abcdefjh"));
        System.out.println(getLengthOfLongestSubstring("abcabcdef"));
        System.out.println(getLengthOfLongestSubstring("abcdefabc"));
    }

    /**
     * Поиск в строке подстроки с неповторяющимися символами наибольшей длины: abcabcbb -> 3 (abc)
     * <p>
     * Сложность: O(n)
     * Сделано в лоб. Можно сделать через два указателя без использования вспомогательных строк
     **/
    private static int getLengthOfLongestSubstring(String input) {
        var result = "";
        var current = new StringBuilder();

        var chars = input.split("");

        for (var c : chars) {
            if (current.indexOf(c) < 0) {
                current.append(c);
            } else {
                if (current.length() > result.length()) {
                    result = current.toString();
                }
                current = new StringBuilder(c);
            }
        }

        return Math.max(result.length(), current.length());
    }
}
