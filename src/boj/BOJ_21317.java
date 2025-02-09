package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 21317. 징검다리 건너기
public class BOJ_21317 {
    static int N;
    static int[] small;
    static int[] big;
    static int K;
    static int MAX = 10000001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        small = new int[N - 1];
        big = new int[N - 1];
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            small[i] = Integer.parseInt(st.nextToken());
            big[i] = Integer.parseInt(st.nextToken());
        }
        K = Integer.parseInt(br.readLine());

        int[][] dp = new int[N][2]; // 0 큰 점프 사용 X, 1 큰 점프 사용 O
        for (int i = 0; i < N; i++) Arrays.fill(dp[i], MAX);
        dp[0][0] = 0;
        dp[0][1] = 0;
        for (int i = 0; i < N - 1; i++) {
            // 작은 점프
            if (i + 1 < N) {
                dp[i + 1][0] = Math.min(dp[i + 1][0], dp[i][0] + small[i]);
                dp[i + 1][1] = Math.min(dp[i + 1][1], dp[i][1] + small[i]);
            }
            // 큰 점프
            if (i + 2 < N) {
                dp[i + 2][0] = Math.min(dp[i + 2][0], dp[i][0] + big[i]);
                dp[i + 2][1] = Math.min(dp[i + 2][1], dp[i][1] + big[i]);
            }

            // 아주 큰 점프
            if (i + 3 < N) {
                dp[i + 3][1] = Math.min(dp[i + 3][1], dp[i][0] + K);
            }
        }
        System.out.println(Math.min(dp[N - 1][0], dp[N - 1][1]));
    }
}
