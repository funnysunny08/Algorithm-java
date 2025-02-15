package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 11060. 점프점프
public class BOj_11060 {
    static int MAX = 1000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        int[] dp = new int[N];
        Arrays.fill(dp, MAX);
        dp[0] = 0;
        for (int i = 0; i < N; i++) {
            if (dp[i] == MAX) continue;
            for (int j = i + 1; j <= i + arr[i] && j < N; j++) {
                dp[j] = Math.min(dp[j], dp[i] + 1);
            }
        }
        if (dp[N - 1] == MAX) {
            System.out.println(-1);
        } else {
            System.out.println(dp[N - 1]);
        }
    }
}
