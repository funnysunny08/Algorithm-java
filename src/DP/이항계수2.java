package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 4C2 = 3C1 + 3C2
 * nCk = (n-1)C(k-1) + (n-1)Ck
 */
// 백준 11051번: 이항 계수 2
public class 이항계수2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] dp = new int[n + 1][1001];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j]) % 10007;
                }
            }
        }
        System.out.println(dp[n][k]);
    }
}
