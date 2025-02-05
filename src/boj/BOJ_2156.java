package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 2156. 포도주 시식
public class BOJ_2156 {
    static int N;
    static int[] arr;
    static Integer[] dp;

    private static int getDp(int n) {
        if (dp[n] == null) {
            dp[n] = Math.max(arr[n] + Math.max(arr[n - 1] + getDp(n - 3), getDp(n - 2)), getDp(n - 1));
        }
        return dp[n];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        for (int i = 1; i <= N; i++) arr[i] = Integer.parseInt(br.readLine());
        dp = new Integer[N + 1];
        dp[0] = 0;
        dp[1] = arr[1];
        if (N > 1) dp[2] = arr[1] + arr[2];
        System.out.println(getDp(N));
    }
}
