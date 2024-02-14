package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 11049번: 행렬 곱셈 순서
public class BOJ_11049 {

    static int N;
    static int[][] matrix;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        matrix = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            matrix[i][0] = Integer.parseInt(st.nextToken());
            matrix[i][1] = Integer.parseInt(st.nextToken());
        }

        dp = new int[N][N];

        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
            dp[i][i] = 0;
        }

        for (int k = 1; k < N; k++) {
            for (int i = 0; i < N && i + k < N; i++) {
                int j = i + k;
                for (int x  = 0; x < j - i; x++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][i + x] + dp[i + x + 1][j] + matrix[i][0] * matrix[i + x][1] * matrix[j][1]);
                }
            }
        }

        System.out.println(dp[0][N - 1]);
    }
}
