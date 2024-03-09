package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 4781번: 사탕 가게
public class BOJ_4781 {

    static int N, M; // 사탕 개수, 예산 * 100
    static long[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        while (true) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = (int) (Double.parseDouble(st.nextToken()) * 100 + 0.5);
            if (N == 0 && M == 0) break;
            dp = new long[M + 1];
            long answer = -1;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int cal = Integer.parseInt(st.nextToken());
                int price = (int) (Double.parseDouble(st.nextToken()) * 100 + 0.5);

                for (int j = 0; j <= M; j++) {
                    if (j + price <= M) {
                        dp[j + price] = Math.max(dp[j + price], dp[j] + cal);
                        answer = Math.max(dp[j + price], answer);
                    }
                }
            }
            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }
}
