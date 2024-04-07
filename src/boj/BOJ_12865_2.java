package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 12865번: 평범한 배낭
public class BOJ_12865_2 {

    static int N, K;
    static int[][] info;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        info = new int[N + 1][2];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            info[i][0] = Integer.parseInt(st.nextToken());
            info[i][1] = Integer.parseInt(st.nextToken());
        }

        dp = new int[K + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = K; j >= 0; j--) {
                if (j - info[i][0] >= 0)
                    dp[j] = Math.max(dp[j], dp[j - info[i][0]] + info[i][1]);
            }
        }
        System.out.println(dp[K]);
    }
}
