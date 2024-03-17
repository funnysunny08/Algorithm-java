package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 백준 18427번: 함께 블록 쌓기
public class BOJ_18427 {

    static int N, M, H;
    static List<Integer>[] blocks;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        blocks = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            blocks[i] = new ArrayList<>();
            String[] str = br.readLine().split(" ");
            for (int j = 0; j < str.length; j++) blocks[i].add(Integer.parseInt(str[j]));
        }

        dp = new int[N + 1][H + 1];
        for (int b : blocks[1]) {
            dp[1][b] = 1;
        }
        for (int n = 2; n <= N; n++) {
            for (int h = 0; h <= H; h++) {
                dp[n][h] = dp[n - 1][h];
                for (int b : blocks[n]) {
                    if (b == h) dp[n][h]++;
                    else if (h - b > 0) dp[n][h] += dp[n - 1][h - b];
                }
                dp[n][h] %= 10007;
            }
        }

        System.out.println(dp[N][H]);
    }
}
