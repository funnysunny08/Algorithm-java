package 오답.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 2629번: 양팔저울
public class BOJ_2629 {

    static int MAX = 40000;
    static int N;
    static int[] arr;
    static boolean[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[N];
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
        dp = new boolean[N + 1][MAX + 1];
        dfs(0, 0);

        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            int val = Integer.parseInt(st.nextToken());
            if (dp[N][val]) sb.append("Y ");
            else sb.append("N ");
        }
        System.out.println(sb);
    }

    public static void dfs(int depth, int w) {
        if (dp[depth][w]) return;
        dp[depth][w] = true;
        if (depth == N) return;

        dfs(depth + 1, w);
        dfs(depth + 1, w + arr[depth]);
        dfs(depth + 1, Math.abs(w - arr[depth]));
    }
}
