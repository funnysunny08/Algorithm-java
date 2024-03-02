package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 백준 3687번: 성냥개비
public class BOJ_3687 {

    static long[] dp;

    public static String getMax(int n) {
        StringBuilder sb = new StringBuilder();
        if (n % 2 != 0) {
            sb.append("7");
            for (int i = 0; i < (n - 3) / 2; i++) sb.append("1");
        } else {
            for (int i = 0; i < n / 2; i++) sb.append("1");
        }
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        dp = new long[101];
        Arrays.fill(dp, Long.MAX_VALUE);
        dp[2] = 1;
        dp[3] = 7;
        dp[4] = 4;
        dp[5] = 2;
        dp[6] = 6;
        dp[7] = 8;
        dp[8] = 10;

        int[] arr = {1, 7, 4, 2, 0, 8}; // 2, 3, 4, 5, 6, 7 => arr[x]: x + 2개로 arr[x] 만들 수 있음!
        for (int i = 9; i <= 100; i++) {
            for (int j = 2; j <= 7; j++) {
                String temp = String.valueOf(dp[i - j]) + String.valueOf(arr[j - 2]);
                dp[i] = Math.min(dp[i], Long.parseLong(temp));
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            sb.append(dp[n]).append(" ").append(getMax(n)).append("\n");
        }
        System.out.println(sb);
    }
}
