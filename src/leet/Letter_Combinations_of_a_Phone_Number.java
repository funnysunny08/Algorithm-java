package leet;

import java.util.ArrayList;
import java.util.List;

// 17. Letter Combinations of a Phone Number
public class Letter_Combinations_of_a_Phone_Number {
    static Character[][] phone = {
        {null, null, null, null},
        {null, null, null, null},
        {'a', 'b', 'c', null},
        {'d', 'e', 'f', null},
        {'g', 'h', 'i', null},
        {'j', 'k', 'l', null},
        {'m', 'n', 'o', null},
        {'p', 'q', 'r', 's'},
        {'t', 'u', 'v', null},
        {'w', 'x', 'y', 'z'}
    };
    static List<String> answer = new ArrayList<>();

    private static void dfs(int idx, String tmp, String digits) {
        if (idx == digits.length()) {
            answer.add(tmp);
            return;
        }
        for (int i = 0; i < 4; i++) {
            if (phone[digits.charAt(idx) - '0'][i] != null) dfs(idx + 1, tmp + phone[digits.charAt(idx) - '0'][i], digits);
        }
    }
    public static List<String> letterCombinations(String digits) {
        if (digits.length() == 0) return answer;
        dfs(0, "", digits);
        return answer;
    }

    public static void main(String[] args) {
        String digits = "";
        List<String> ans = letterCombinations(digits);
        for (int i = 0; i < ans.size(); i++) {
            System.out.println(ans.get(i));
        }
    }
}
