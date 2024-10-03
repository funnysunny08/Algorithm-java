package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 2011. 암호코드
public class BOJ_2011 {
    private static final int MOD = 1000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String code = br.readLine();

        int[] dp = new int[code.length() + 1];
        dp[0] = 1;

        for (int i = 1; i <= code.length(); i++) {
            int now = code.charAt(i - 1) - '0';
            if (now >= 1 && now <= 9) dp[i] = (dp[i] + dp[i - 1]) % MOD;
            if (i == 1) continue;

            int prev = code.charAt(i - 2) - '0';
            if (prev == 0) continue;
            int value = prev * 10 + now;
            if (value >= 10 && value <= 26) dp[i] = (dp[i] + dp[i - 2]) % MOD;
        }
        System.out.println(dp[code.length()]);
    }
}
