package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 백준 10422번: 괄호
public class BOJ_10422 {

    static Long[] dp = new Long[5001];

    static long getDp(int length) {
        if (length % 2 != 0) return 0;
        if (dp[length] != null) return dp[length];

        dp[length] = 0L;
        for (int x = 0; x <= length - 2; x++) {
            int y = length - x - 2;
            dp[length] += getDp(x) * getDp(y);
            dp[length] %= 1000000007;
        }
        return dp[length];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        dp[0] = 1L;
        dp[2] = 1L;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            int value = Integer.parseInt(br.readLine());
            sb.append(getDp(value)).append("\n");
        }
        System.out.println(sb);
    }
}
