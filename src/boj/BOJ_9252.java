package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 백준 9252번: LCS 2
public class BOJ_9252 {

    static String str1;
    static String str2;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str1 = br.readLine();
        str2 = br.readLine();
        dp = new int[str1.length() + 1][str2.length() + 1];

        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        int cnt = dp[str1.length()][str2.length()];
        int r = str1.length();
        int c = str2.length();
        while (cnt > 0) {
            if (dp[r][c] == dp[r - 1][c]) {
                r--;
            } else if (dp[r][c] == dp[r][c - 1]) {
                c--;
            } else {
                sb.append(str1.charAt(r - 1));
                r--;
                c--;
                cnt--;
            }
        }
        System.out.println(dp[str1.length()][str2.length()]);
        System.out.println(sb.reverse());
    }
}
