package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 평범한배낭2 {

    static int[][] dp;
    static int[] W; // 무게
    static int[] V; // 가치

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        W = new int[N + 1];
        V = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            W[i] = Integer.parseInt(st.nextToken());
            V[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[N + 1][K + 1];

        for (int k = 0; k <= K; k++) { // 무게
            for (int i = 1; i <= N; i++) { // 아이템 index
                dp[i][k] = dp[i - 1][k]; // 일단 직전 dp로 갱신하고
                if (W[i] <= k) { // 현재 item 담을 수 있음!
                    dp[i][k] = Math.max(dp[i - 1][k], dp[i - 1][k - W[i]] + V[i]);
                }
            }
        }

        System.out.println(dp[N][K]);
    }
}
