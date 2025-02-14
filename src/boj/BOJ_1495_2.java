package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 1495. 기타리스트
public class BOJ_1495_2 {
    static int N, S, M;
    static int[] arr;
    static boolean[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) arr[i] = Integer.parseInt(st.nextToken());
        dp = new boolean[N + 1][M + 1];

        dp[0][S] = true;
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= M; j++) {
                if (dp[i - 1][j]) {
                    if (j + arr[i] <= M) dp[i][j + arr[i]] = true;
                    if (j - arr[i] >= 0) dp[i][j - arr[i]] = true;
                }
            }
        }

        for (int i = M; i >= 0; i--) {
            if (dp[N][i]) {
                System.out.println(i);
                return;
            }
        }
        System.out.println(-1);
    }
}
