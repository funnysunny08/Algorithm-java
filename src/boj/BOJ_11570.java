package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 11570. 환상의 듀엣
public class BOJ_11570 {
    static int N;
    static int[] music;
    static Long[][] dp;

    private static long dfs(int idx, int a, int b) {
        if (idx > N) return 0;
        if (dp[a][b] != null) return dp[a][b];

        // idx 번째 음을 A가 연주한다
        long aa = dfs(idx + 1, idx, b) + getDiff(a, idx);
        // idx 번쨰 음을 B가 연주한다
        long bb = dfs(idx + 1, a, idx) + getDiff(b, idx);
        return dp[a][b] = Math.min(aa, bb);
    }

    private static int getDiff(int start, int end) {
        if (start == 0) return 0;
        return Math.abs(music[start] - music[end]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        music = new int[N + 1];
        for (int i = 1; i <= N; i++) music[i] = Integer.parseInt(st.nextToken());

        dp = new Long[N + 1][N + 1];
        System.out.println(dfs(1, 0, 0));
    }
}
