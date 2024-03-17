package 오답.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 10653번: 마라톤 2
public class BOJ_10653 {

    static int N, K;
    static Integer[][] dist;
    static int[][] dp;
    static int[][] info;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        info = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            info[i][0] = Integer.parseInt(st.nextToken());
            info[i][1] = Integer.parseInt(st.nextToken());
        }

        dist = new Integer[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (dist[i][j] != null) continue;
                dist[i][j] = Math.abs(info[i][0] - info[j][0]) + Math.abs(info[i][1] - info[j][1]);
                dist[j][i] = dist[i][j];
            }
        }

        dp = new int[K + 1][N];
        for (int i = 1; i < N; i++) {
            dp[0][i] = dp[0][i - 1] + dist[i][i - 1];
        }

        for (int k = 1; k <= K; k++) {
            for (int n = 1; n < N; n++) {
                dp[k][n] = Integer.MAX_VALUE;
                for (int d = 0; d <= k && n - 1 - d >= 0; d++) {
                    dp[k][n] = Math.min(dp[k][n], dp[k - d][n - 1 - d] + dist[n - 1 - d][n]);
                }
            }
        }
        System.out.println(dp[K][N - 1]);
    }
}
