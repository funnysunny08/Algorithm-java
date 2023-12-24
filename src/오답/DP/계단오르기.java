package 오답.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 백준 2579번: 계단 오르기
public class 계단오르기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] stairs = new int[n];
        for (int i = 0; i < n; i++) {
            stairs[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[n];

        if (n <= 2) {
            int sum = 0;
            for (int i = 0; i < n; i++) {
                sum += stairs[i];
            }
            System.out.println(sum);
            System.exit(0);
        }

        dp[0] = stairs[0];
        dp[1] = stairs[0] + stairs[1];
        dp[2] = Math.max(stairs[0], stairs[1]) + stairs[2];
        for (int i = 3; i < n; i++) {
            dp[i] = Math.max(stairs[i - 1] + dp[i - 3], dp[i - 2]) + stairs[i];
        }
        System.out.println(dp[n - 1]);
    }
}
