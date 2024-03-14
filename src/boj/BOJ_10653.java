package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 10653번: 마라톤 2
public class BOJ_10653 {

    static int N, K;
    static int[][] coordinate;
    static Integer[][] dist;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        coordinate = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            coordinate[i][0] = Integer.parseInt(st.nextToken());
            coordinate[i][1] = Integer.parseInt(st.nextToken());
        }
        dist = new Integer[N][N];
        dp = new int[K + 1][N];
        for (int i = 1; i < N; i++) {
            dp[0][i] = dp[0][i - 1] + getDistance(i, i - 1);
        }

        for (int k = 1; k <= K; k++) {
            for (int n = 1; n < N; n++) {
                // k + 1 번 반복해야 함!
                dp[k][n] = Integer.MAX_VALUE;
                for (int d = 0; d <= k && n - 1 - d >= 0; d++) {
                    dp[k][n] = Math.min(dp[k][n], dp[k - d][n - 1 - d] + getDistance(n - 1 - d, n));
                }
            }
        }
        System.out.println(dp[K][N - 1]);
    }

    public static int getDistance(int a, int b) {
        if (dist[a][b] == null) {
            int distance = Math.abs(coordinate[a][0] - coordinate[b][0]) + Math.abs(coordinate[a][1] - coordinate[b][1]);
            dist[a][b] = distance;
            dist[b][a] = distance;
        }
        return dist[a][b];
    }
}
