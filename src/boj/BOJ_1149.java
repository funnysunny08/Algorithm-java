package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 1149. RGB 거리
public class BOJ_1149 {
    static int N;
    static int[][] colors;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        colors = new int[N][3];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            colors[i][0] = Integer.parseInt(st.nextToken());
            colors[i][1] = Integer.parseInt(st.nextToken());
            colors[i][2] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N][3];
        dp[0][0] = colors[0][0];
        dp[0][1] = colors[0][1];
        dp[0][2] = colors[0][2];

        for (int i = 1; i < N; i++) {
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + colors[i][0];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + colors[i][1];
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + colors[i][2];
        }
        System.out.println(Math.min(dp[N - 1][0], Math.min(dp[N - 1][1], dp[N - 1][2])));
    }
}
