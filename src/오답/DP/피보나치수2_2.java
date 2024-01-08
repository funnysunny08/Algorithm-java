package 오답.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 백준 2748번: 피보나치 수2
public class 피보나치수2_2 {

    static long[] dp;

    public static long fibonacci(int n) {
        if (dp[n] == -1) {
            dp[n] = fibonacci(n - 1) + fibonacci(n - 2);
        }
        return dp[n];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        dp = new long[n + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        dp[1] = 1;
        System.out.println(fibonacci(n));
    }
}
