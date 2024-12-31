package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_9095_2 {
    static Integer[] dp = new Integer[12];

    private static int getDP(int num) {
        if (dp[num] != null) return dp[num];
        dp[num] = getDP(num - 1) + getDP(num - 2) + getDP(num - 3);
        return dp[num];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            int target = Integer.parseInt(br.readLine());
            sb.append(getDP(target)).append("\n");
        }
        System.out.println(sb);
    }
}
