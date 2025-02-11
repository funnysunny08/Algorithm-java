package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 1495. 기타리스트
public class BOJ_1495 {
    static int N, S, M;
    static int[] volume;

    private static int DP() {
        boolean[][] dp = new boolean[N + 1][M + 1];
        dp[0][S] = true;
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= M; j++) {
                if (dp[i - 1][j]) {
                    if (j + volume[i] <= M) dp[i][j + volume[i]] = true;
                    if (j - volume[i] >= 0) dp[i][j - volume[i]] = true;
                }
            }
        }

        int max = -1;
        for (int i = 0; i <= M; i++) {
            if (dp[N][i]) max = i;
        }
        return max;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        volume = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) volume[i] = Integer.parseInt(st.nextToken());
        System.out.println(DP());
    }
}
