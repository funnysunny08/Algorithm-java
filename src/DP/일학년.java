package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 5557번: 1학년
public class 일학년 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr =  new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        long[][] dp = new long[n - 1][21];
        dp[0][arr[0]] = 1;
        for (int i = 1; i <= n - 2; i++) { // arr[i]까지 이용했을 때
            for (int j = 0; j <= 20; j++) { // j를 몇 번 만들 수 있는가
                if (dp[i - 1][j] != 0) {
                    // 만들 수 있는 숫자: j + arr[i], j - arr[i]
                    if (j + arr[i] >= 0 && j + arr[i] <= 20) {
                        dp[i][j + arr[i]] += dp[i - 1][j];
                    }

                    if (j - arr[i] >= 0 && j - arr[i] <= 20) {
                        dp[i][j - arr[i]] += dp[i - 1][j];
                    }
                }
            }
        }

        System.out.println(dp[n - 2][arr[n - 1]]);
    }
}
