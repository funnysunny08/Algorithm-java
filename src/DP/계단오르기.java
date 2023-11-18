package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 백준 2579번: 계단 오르기
public class 계단오르기 {

    static int[] stairs;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        stairs = new int[n + 1];
        dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            stairs[i] = Integer.parseInt(br.readLine());
        }

        if (n <= 2) {
            int sum = 0;
            for (int i = 1; i <= n; i++) {
                sum += stairs[i];
            }
            System.out.println(sum);
            System.exit(0);
        }

        dp[0] = 0; // 바닥
        dp[1] = stairs[1]; // 1번째 계단
        dp[2] = stairs[1] + stairs[2]; // 1번째 계단을 밟고 올라오는 게 당연히 최대값

        for (int i = 3; i <= n; i++) {
            dp[i] = Math.max(dp[i -2], dp[i - 3] + stairs[i - 1]) + stairs[i];
        }

        System.out.println(dp[n]);
    }
}
