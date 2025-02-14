package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 9465. 스티커
public class BOJ_9465_2 {
    static int N;
    static int[][] board;
    static int[][] dp;

    private static void solve() {
        dp = new int[2][N + 1];
        dp[0][1] = board[0][1];
        dp[1][1] = board[1][1];
        for (int i = 2; i <= N; i++) {
            dp[0][i] = Math.max(dp[1][i - 1], dp[1][i - 2]) + board[0][i];
            dp[1][i] = Math.max(dp[0][i - 1], dp[0][i - 2]) + board[1][i];
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());
            board = new int[2][N + 1];
            for (int i = 0; i < 2; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 1; j <= N; j++) board[i][j] = Integer.parseInt(st.nextToken());
            }
            solve();
            sb.append(Math.max(dp[0][N], dp[1][N])).append("\n");
        }
        System.out.println(sb);
    }
}
