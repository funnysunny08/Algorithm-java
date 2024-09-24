package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 2302. 극장좌석
public class BOJ_2302 {
    static int[] dp = new int[41];
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= 40; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
            // 첫번쨰 자리 스위치하지 않고 나머지 n - 1 자리 재배치
            // +
            // 첫번쨰 자리 스위치하고 나머지 n - 2 자리 재비치
        }

        int answer = 1;
        int idx = 1;
        for (int i = 0; i < M; i++) {
            int vip = Integer.parseInt(br.readLine());
            answer *= dp[vip - idx];
            idx = vip + 1;
        }
        answer *= dp[N - idx + 1];

        System.out.println(answer);
    }
}
