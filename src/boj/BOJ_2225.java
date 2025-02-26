package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 2225. 합분해
public class BOJ_2225 {
    private static final int DIV = 1000000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] dp = new int[K + 1][N + 1];
        for (int i = 0; i <= N; i++) dp[1][i] = 1;

        for (int i = 2; i <= K; i++) {
            for (int j = 0; j <= N; j++) {
                if (j > 0) dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % DIV;
                else dp[i][j] = dp[i - 1][j];
            }
        }
        System.out.println(dp[K][N] % DIV);
    }
}
