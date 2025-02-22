package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 10844. 쉬운 계단 수
public class BOJ_10844_2 {
    static int MOD = 1000000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[][] dp = new long[n + 1][10];
        for (int i = 1; i <= 9; i++) dp[1][i] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j <= 9; j++) {
                if (j > 0) dp[i][j] = (dp[i][j] + dp[i - 1][j - 1]) % MOD;
                if (j < 9) dp[i][j] = (dp[i][j] + dp[i - 1][j + 1]) % MOD;
            }
        }

        long sum = 0;
        for (int i = 0; i <= 9; i++) sum += dp[n][i];
        System.out.println(sum % MOD);
    }
}
