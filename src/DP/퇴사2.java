package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 15486번: 퇴사 2
public class 퇴사2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int result = -1;
        int[] dp = new int[n + 2];
        int maxDp = 0;
        StringTokenizer st;
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());

            int day = i + t - 1; // i번째 날의 일을 수락했을 때, 해당 일이 끝나는 날
            if (day > n) continue;

            dp[day] = Math.max(dp[day], p + maxDp);

            result = Math.max(dp[day], result);
            maxDp = Math.max(maxDp, dp[i]);
        }
        System.out.println(result);
    }
}
