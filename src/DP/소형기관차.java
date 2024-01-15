package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 2616번: 소형 기관차
public class 소형기관차 {

    static int n; // 객차의 수
    static int[] sum; // 객차별 손님 수 배열의 누적합
    static int max; // 소형 기관차 1대가 끌 수 있는 객차의 수
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        sum = new int[n + 1];
        for (int i = 1; i <= n; i++) { // 누적합
            sum[i] = Integer.parseInt(st.nextToken()) + sum[i - 1];
        }
        max = Integer.parseInt(br.readLine());
        dp = new int[4][n + 1];

        for (int i = 1; i <= 3; i++) {
            for (int j = i * max; j <= n; j++) {
                dp[i][j] = Math.max(
                    dp[i][j - 1], // (j - max + 1) ~ (j) 번 열차를 안 끄는 경우
                    dp[i - 1][j - max] + (sum[j] - sum[j - max])
                    // (j - max + 1) ~ (j) 번 열차를 포함하기 때문에,
                    // (j - max + 1) ~ (j) 번 열차의 승객 합 더하고
                    // (j - max + 1) ~ (j) 번 열차를 포함하지 않았을 때의 dp 값 가져온다!
                );
            }
        }
        System.out.println(dp[3][n]);
    }
}
