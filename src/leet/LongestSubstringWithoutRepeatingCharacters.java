package leet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

// Longest Substring Without Repeating Characters
public class LongestSubstringWithoutRepeatingCharacters {

    static int answer = 1;
    static String str;
    public static int lengthOfLongestSubstring(String s) {
        if (s.length() <= 1) return s.length();
        str = s;
        for (int i = 2; i <= s.length(); i++) {
            if (check(i)) {
                answer++;
            } else {
                return answer;
            }
        }
        return answer;
    }

    private static boolean check(int length) {
        int start = 0;
        int end = length - 1;
        List<Character> list = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            list.add(str.charAt(i));
        }

        while (end <= str.length()) {
            if (new HashSet<>(list).size() == length) return true;
            if (end == str.length() - 1) return false;
            list.remove(0);
            list.add(str.charAt(end + 1));
            start++;
            end++;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("pwwkew"));
    }
}
