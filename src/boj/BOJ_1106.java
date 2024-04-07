package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 1106번: 호텔
public class BOJ_1106 {

    static int C, N;
    static int[][] info;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        info = new int[N + 1][2];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            info[i][0] = Integer.parseInt(st.nextToken());
            info[i][1] = Integer.parseInt(st.nextToken());
        }

        int money = 100000;
        dp = new int[N + 1][money + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= money; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (j - info[i][0] >= 0) dp[i][j] = Math.max(dp[i][j], dp[i][j - info[i][0]] + info[i][1]);

                if (dp[i][j] >= C) {
                    money = Math.min(money, j);
                    break;
                }
            }
        }
        System.out.println(money);
    }
}
