package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 9465. 스티커
public class BOJ_9465 {
    static int N;
    static int[][] map;
    static int[][] dp; // 해당 스티커를 취했을 때

    private static void play() {
        dp = new int[2][N + 1];
        dp[0][1] = map[0][1];
        dp[1][1] = map[1][1];
        for(int j = 2; j < N + 1; j++) {
            dp[0][j] = Math.max(dp[1][j - 1], dp[1][j - 2]) + map[0][j];
            dp[1][j] = Math.max(dp[0][j - 1], dp[0][j - 2]) + map[1][j];
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());
            map = new int[2][N + 1];
            for (int i = 0; i < 2; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 1; j <= N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            play();
            sb.append(Math.max(dp[0][N], dp[1][N])).append("\n");
        }
        System.out.println(sb);
    }
}
