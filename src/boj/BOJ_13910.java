package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 13910번: 개업
public class BOJ_13910 {

    static int N, M;
    static final int MAX = 10001;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[] woks = new int[MAX];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            woks[Integer.parseInt(st.nextToken())]++;
        }

        dp = new int[N + 1];
        Arrays.fill(dp, MAX);
        dp[0] = 0;
        woks[0] = 1;

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= i / 2; j++) {
                if (j == i - j && woks[j] >= 2) {
                    dp[i] = 1;
                } else if (j != i - j && woks[j] > 0 && woks[i - j] > 0) {
                    dp[i] = 1;
                } else if (dp[j] != MAX && dp[i - j] != MAX) {
                    dp[i] = Math.min(dp[i], dp[j] + dp[i - j]);
                }
            }
        }
        System.out.println(dp[N] == MAX ? -1 : dp[N]);
    }
}
