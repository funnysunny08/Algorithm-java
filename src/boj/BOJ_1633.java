package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 1633. 최고의 팀 만들기
public class BOJ_1633 {
    static int N;
    static int[] white = new int[1001];
    static int[] black = new int[1001];
    static int[][][] dp; // (n, w, b)
    // i번째 선수까지 선택했을 때 백팀 w명, 흑팀 b명일 때의 능력치 최댓값

    private static int getDp(int n, int w, int b) {
        if (n == 0) return 0;
        if (w == 0 && b == 0) return 0;
        if (dp[n][w][b] != 0) return dp[n][w][b];

        dp[n][w][b] = Math.max(dp[n][w][b], getDp(n - 1, w, b));
        if (w > 0) dp[n][w][b] = Math.max(dp[n][w][b], getDp(n - 1, w - 1, b) + white[n]);
        if (b > 0) dp[n][w][b] = Math.max(dp[n][w][b], getDp(n - 1, w, b - 1) + black[n]);
        return dp[n][w][b];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String s;

        while ((s = br.readLine()) != null) {
            N++;
            st = new StringTokenizer(s);
            white[N] = Integer.parseInt(st.nextToken());
            black[N] = Integer.parseInt(st.nextToken());
        }

        dp = new int[N + 1][16][16];
        System.out.println(getDp(N, 15,15));
    }
}
