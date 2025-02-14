package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 2156. 포도주 시식
public class BOJ_2156_2 {
    static int N;
    static int[] arr;
    static Integer[] dp;

    private static int getDp(int num) {
        if (num < 0) return 0;
        if (dp[num] != null) return dp[num];
        dp[num] = getDp(num - 1);
        dp[num] = Math.max(dp[num], arr[num] + arr[num - 1] + getDp(num - 3));
        dp[num] = Math.max(dp[num], arr[num] + getDp(num - 2));
        return dp[num];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        for (int i = 1; i <= N; i++) arr[i] = Integer.parseInt(br.readLine());
        dp = new Integer[N + 1];
        dp[0] = 0;
        if (N > 0) dp[1] = arr[1];
        if (N > 1) dp[2] = arr[1] + arr[2];
        System.out.println(getDp(N));
    }
}
