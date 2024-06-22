package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 백준 2688번: 줄어들지 않아
public class BOJ_2688 {

    static Long[][] dp = new Long[65][10];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Arrays.fill(dp[1], 1L);

        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            int number = Integer.parseInt(br.readLine());
            long sum = 0;
            for (int j = 0; j <= 9; j++) sum += getDp(number, j);
            sb.append(sum).append("\n");
        }
        System.out.println(sb);
    }

    private static long getDp(int n, int idx) {
        if (dp[n][idx] != null) return dp[n][idx];
        dp[n][idx] = 0L;
        for (int i = idx; i <= 9; i++) {
            dp[n][idx] += getDp(n - 1, i);
        }
        return dp[n][idx];
    }
}
