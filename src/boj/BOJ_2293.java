package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 2293번: 동전 1
public class BOJ_2293 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] coins = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        int[][] dp = new int[N + 1][K + 1];

        for (int n = 1; n <= N; n++) {
            for (int k = 1; k <= K; k++) {
                if (k == coins[n]) dp[n][k] = dp[n - 1][k] + 1;
                else if (coins[n] > k) dp[n][k] = dp[n - 1][k];
                else dp[n][k] = dp[n - 1][k] + dp[n][k - coins[n]];
            }
        }

        for (int n = 1; n <= N; n++) {
            for (int k = 1; k <= K; k++) {
                System.out.print(dp[n][k] + " ");
            }
            System.out.println();
        }

        System.out.println(dp[N][K]);
    }
}
