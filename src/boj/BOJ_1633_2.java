package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.StringTokenizer;

// 1633. 최고의 팀 만들기
public class BOJ_1633_2 {
    static int[] white = new int[1001];
    static int[] black = new int[1001];
    static int N;
    static int[][][] dp;

    private static int getDp(int i, int w, int b) {
        if (i == 0 || (w == 0 && b == 0)) return 0;
        if (dp[i][w][b] != 0) return dp[i][w][b];
        dp[i][w][b] = Math.max(dp[i][w][b], getDp(i - 1, w, b));
        if (w > 0) dp[i][w][b] = Math.max(dp[i][w][b], getDp(i - 1, w - 1, b) + white[i]);
        if (b > 0) dp[i][w][b] = Math.max(dp[i][w][b], getDp(i - 1, w, b - 1) + black[i]);
        return dp[i][w][b];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String s = br.readLine();
        while (!Objects.equals(s, "")) {
            N++;
            st = new StringTokenizer(s);
            white[N] = Integer.parseInt(st.nextToken());
            black[N] = Integer.parseInt(st.nextToken());
            s = br.readLine();
        }
        dp = new int[N + 1][16][16];
        System.out.println(getDp(N, 15,15));
    }
}
