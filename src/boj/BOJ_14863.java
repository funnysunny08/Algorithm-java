package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 14863번: 서울에서 경산까지
public class BOJ_14863 {

    static int  N, K;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dp = new int[N + 1][K + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int t1 = Integer.parseInt(st.nextToken()); // 도보
            int m1 = Integer.parseInt(st.nextToken());
            int t2 = Integer.parseInt(st.nextToken()); // 자전거
            int m2 = Integer.parseInt(st.nextToken());

            for (int j = 0; j <= K; j++) {
                if (j != 0) dp[i][j] = dp[i][j - 1];
                if (j >= t1) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - t1] + m1);
                }
                if (j >= t2) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - t2] + m2);
                }
            }
        }
        System.out.println(dp[N][K]);
    }
}
