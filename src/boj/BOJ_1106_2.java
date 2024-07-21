package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 1106번: 호텔
public class BOJ_1106_2 {

    static int C, N;
    static Integer[] dp = new Integer[100001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        dp[0] = 0;
        int c, p;
        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            c = Integer.parseInt(st.nextToken());
            p = Integer.parseInt(st.nextToken());
            for (int j = 0; j < 100001; j++) {
                if (dp[j] != null) {
                    if (j + c < 100001 && dp[j + c] == null) dp[j + c] = dp[j] + p;
                    else if (j + c < 100001 && dp[j + c] != null) dp[j + c] = Math.max(dp[j + c], dp[j] + p);

                    if (dp[j] >= C) {
                        answer = Math.min(answer, j);
                        break;
                    }
                }
            }
        }
        System.out.println(answer);
    }
}
