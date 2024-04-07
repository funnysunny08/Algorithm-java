package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 2629번: 양팔저울
public class BOJ_2629 {

    static int N, M;
    static boolean[][] dp;
    static int[] w;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        w = new int[N];
        for (int i = 0; i < N; i++) {
            w[i] = Integer.parseInt(st.nextToken());
        }

        dp = new boolean[N + 1][40001];
        dp(0,0);
        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            int x = Integer.parseInt(st.nextToken());
            if (dp[N][x]) sb.append("Y ");
            else sb.append("N ");
        }
        System.out.println(sb);
    }

    public static void dp(int idx, int num) {
        // idx 까지의 추를 고려했을 때, num 을 만들 수 있는지!
        if (dp[idx][num]) return;
        dp[idx][num] = true;

        if (idx == N) return;

        dp(idx + 1, num + w[idx]);
        dp(idx + 1, num);
        dp(idx + 1, Math.abs(num - w[idx]));
    }
}
