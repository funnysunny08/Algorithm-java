package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 2073번: 수도배관공사
public class BOJ_2073 {

    static int D, P;
    static int[][] pipes;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        D = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        pipes = new int[P + 1][2];
        dp = new int[P + 1][D + 1];
        for (int i = 0; i <= P; i++) Arrays.fill(dp[i], -1);

        for (int i = 1; i <= P; i++) {
            st = new StringTokenizer(br.readLine());
            pipes[i][0] = Integer.parseInt(st.nextToken()); // 길이
            pipes[i][1] = Integer.parseInt(st.nextToken()); // 용량
        }

        for (int i = 1; i <= P; i++) {
            dp[i][pipes[i][0]] = Math.max(dp[i - 1][pipes[i][0]], pipes[i][1]);
            for (int j = 1; j <= D; j++) {
                if (dp[i - 1][j] == -1 || j + pipes[i][0] > D) continue;
                int newValue = Math.min(dp[i - 1][j], pipes[i][1]);
                dp[i][j + pipes[i][0]] = Math.max(dp[i - 1][j + pipes[i][0]], newValue);
            }
            for (int j = 1; j <= D; j++) dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
        }
        System.out.println(dp[P][D]);
    }
}
