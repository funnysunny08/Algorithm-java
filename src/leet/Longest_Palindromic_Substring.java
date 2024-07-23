package leet;

//5. Longest Palindromic Substring
public class Longest_Palindromic_Substring {
    static int max_length = -1;
    static String answer;
    static String original;

    public static void main(String[] args) {
        System.out.println(longestPalindrome("babad"));
    }

    public static String longestPalindrome(String s) {
        int[] selected = new int[2];
        original = s;
        choice(0, selected, 0);
        if (max_length == -1) return s.substring(0, 1);
        return answer;
    }

    public static void choice(int depth, int[] selected, int s) {
        if (depth == 2) {
            String newStr = original.substring(selected[0], selected[1] + 1);
            if (check(newStr) && max_length < newStr.length()) {
                answer = newStr;
                max_length = newStr.length();
            }
            return;
        }
        for (int i = s; i < original.length(); i++) {
            selected[depth] = i;
            choice(depth + 1, selected, i + 1);
        }
    }

    public static boolean check(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }
}
