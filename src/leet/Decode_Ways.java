package leet;

// 91. Decode Ways
public class Decode_Ways {
    public static int numDecodings(String s) {
        int[] dp = new int[s.length()];

        int first = s.charAt(0) - '0';
        if (first == 0) return 0;
        dp[0] = 1;

        int second;

        if (s.length() >= 2) {
            second = s.charAt(1) - '0';
            if (second != 0) dp[1] = dp[0];
            else if (first != 1 && first != 2) return 0;

            int num = first * 10 + second;
            if (num >= 10 && num <= 26) dp[1] += 1;
        }
        else return dp[0];
        first = second;

        for (int i = 2; i < s.length(); i++) {
            second = s.charAt(i) - '0';
            if (second != 0) dp[i] = dp[i - 1];
            else if (first != 1 && first != 2) return 0;

            int num = first * 10 + second;
            if (num >= 10 && num <= 26) dp[i] += dp[i - 2];
            first = second;
        }
        return dp[s.length() - 1];
    }

    public static void main(String[] args) {
        System.out.println(numDecodings("12"));
    }
}
