package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 14863번: 서울에서 경산까지
public class BOJ_14863_2 {

    static int N, K;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dp = new int[N + 1][K + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int t1 = Integer.parseInt(st.nextToken());
            int m1 = Integer.parseInt(st.nextToken());
            int t2 = Integer.parseInt(st.nextToken());
            int m2 = Integer.parseInt(st.nextToken());

            if (i == 1) {
                dp[i][t1] = m1;
                dp[i][t2] = Math.max(dp[i][t2], m2); // t2 == t1 일수도 있음!
            } else {
                for (int j = 1; j <= K; j++) {
                    if (dp[i - 1][j] == 0) continue;
                    if (j + t1 <= K) {
                        dp[i][j + t1] = Math.max(dp[i][j + t1], dp[i - 1][j] + m1);
                    }

                    if (j + t2 <= K) {
                        dp[i][j + t2] = Math.max(dp[i][j + t2], dp[i - 1][j] + m2);
                    }
                }
            }
        }

        int ans = -1;
        for (int j = 1; j <= K; j++) {
            ans = Math.max(ans, dp[N][j]);
        }
        System.out.println(ans);
    }
}
