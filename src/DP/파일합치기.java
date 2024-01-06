package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 11066번: 파일 합치기
public class 파일합치기 {

    static int n;
    static int[] arr;
    static int[][] dp;
    static int[] sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int tests = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < tests; t++) {
            n = Integer.parseInt(br.readLine());
            arr = new int[n + 1];
            dp = new int[n + 1][n + 1];
            sum = new int[n + 1];

            st = new StringTokenizer(br.readLine());

            for (int l = 1; l <= n; l++) {
                arr[l] = Integer.parseInt(st.nextToken());
                sum[l] = arr[l] + sum[l - 1];
            }

            fileMerge();
            sb.append(dp[1][n]).append("\n");
        }
        System.out.println(sb);
    }

    public static void fileMerge() {
        for (int k = 1; k < n; k++) { // interval number
            for (int x = 1; x + k <= n; x++) {
                // dp[x][x + k]
                int y = x + k;
                dp[x][y] = Integer.MAX_VALUE;

                for (int j = x; j < y; j++) {
                    dp[x][y] = Math.min(dp[x][y], dp[x][j] + dp[j + 1][y] + sum[y] - sum[x - 1]);
                }
            }
        }
    }
}
