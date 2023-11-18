package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 12865번: 평범한 배낭
public class 평범한배낭 {

    static Integer[][] dp;
    static int[] W; // 무게
    static int[] V; // 가치

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        W = new int[N];
        V = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            W[i] = Integer.parseInt(st.nextToken());
            V[i] = Integer.parseInt(st.nextToken());
        }

        dp = new Integer[N][K + 1];
        System.out.println(knapsack(N - 1, K));
    }

    static int knapsack(int i, int k) {
        if (i < 0) {
            return 0;
        }
        if (dp[i][k] == null) {
            if (W[i] > k) { // 배낭에 못 담음!
                dp[i][k] = knapsack(i - 1, k);
            } else {
                dp[i][k] = Math.max(knapsack(i - 1, k - W[i]) + V[i], knapsack(i - 1, k));
            }
        }
        return dp[i][k];
    }
}
