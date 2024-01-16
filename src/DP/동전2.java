package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

// 백준 2294번: 동전 2
public class 동전2 {

    static int n, k;
    static Set<Integer> coins = new HashSet<>();
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            coins.add(Integer.parseInt(br.readLine()));
        }
        dp = new int[k + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);

        for (int i = 1; i <= k; i++) {
            if (coins.contains(i)) { // i원의 동전 종류가 있는 경우.
                dp[i] = 1;
                continue;
            }
            for (int x = 1; x <= i / 2; x++) {
                int y = i - x; // x + y = i

                if (dp[x] != Integer.MAX_VALUE && dp[y] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[x] + dp[y]);
                }
            }
        }
        if (dp[k] == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(dp[k]);
        }
    }
}
