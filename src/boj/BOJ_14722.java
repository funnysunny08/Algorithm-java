package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 14722번: 우유 도시
public class BOJ_14722 {

    static int N;
    static int[][] map;
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) map[i][j] = Integer.parseInt(st.nextToken());
        }

        dp = new int[N + 1][N + 1][3];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                for (int m = -1; m < 2; m++) {
                    int nextMilk = (m + 1) % 3; // 현재 우유
                    int milk = (nextMilk + 5) % 3; // 직전 우유

                    if (nextMilk == 0 && nextMilk == map[i][j]) {
                        dp[i][j][nextMilk] = Math.max(dp[i][j][nextMilk], Math.max(dp[i - 1][j][milk], dp[i][j - 1][milk]) + 1);
                    } else if (nextMilk == map[i][j] && dp[i][j][milk] > dp[i][j][nextMilk]) { // 직전 우유를 마셨는지 확인
                        dp[i][j][nextMilk] = Math.max(dp[i][j][nextMilk], Math.max(dp[i - 1][j][milk], dp[i][j - 1][milk]) + 1);
                    } else {
                        dp[i][j][nextMilk] = Math.max(dp[i][j][nextMilk], Math.max(dp[i - 1][j][nextMilk], dp[i][j - 1][nextMilk]));
                    }
                }
            }
        }
        int max = 0;
        for (int i = 0; i < 3; i++) max = Math.max(max, dp[N][N][i]);
        System.out.println(max);
    }
}
