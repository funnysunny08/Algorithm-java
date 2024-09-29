package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 백준 1309번: 동물원
public class BOJ_1309 {

    static int N;
    static int[][] dp;
    static int MOD = 9901;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N + 1][3];

        dp[1][0] = dp[1][1] = dp[1][2] = 1;
        for (int i = 2; i <= N; i++) {
            // i번째 줄에 사자를 배치하지 않는 경우
            dp[i][0] = (dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2]) % MOD;
            // i번째 줄에서 첫번째 칸에 사자를 배치하는 경우
            dp[i][1] = (dp[i - 1][0] + dp[i - 1][2]) % MOD;
            // i번째 줄에서 두번째 칸에 사자를 배치하는 경우
            dp[i][2] = (dp[i - 1][0] + dp[i - 1][1]) % MOD;
        }
        System.out.println((dp[N][0] + dp[N][1] + dp[N][2]) % MOD);
    }
}
