package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 11049번: 행렬 곱셈 순서
public class 행렬곱셈순서 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][2];
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int k = 1; k <= n; k++) { // 간격
            for (int x = 0; x < n; x++) {
                int y = x + k;
                if (y >= n) continue;
                // dp[x][y]
                dp[x][y] = Integer.MAX_VALUE;
                for (int i = x; i < y; i++) {
                    // dp[x][i] dp[i + 1][y]
                    int value = arr[x][0] * arr[i][1] * arr[y][1];
                    dp[x][y] = Math.min(dp[x][y], dp[x][i] + dp[i + 1][y] + value);
                }
            }
        }

        System.out.println(dp[0][n - 1]);
    }
}
