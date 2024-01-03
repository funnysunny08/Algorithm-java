package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 11722번: 가장 긴 감소하는 부분 수열
public class 가장긴감소하는부분수열 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 수열의 크기
        int[] arr = new int[n];
        int[] dp = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int result = -1;
        // 감소해야 함!!
        // 현재의 원소보다 작은 idx && 현재 원소보다 큰 값 중에서 => 가장 큰 dp 값 + 1
        for (int i = 0; i < n; i++) {
            int maxDP = 0; // 0으로 초기화합니다.
            for (int j = 0; j < i; j++) {
                if (arr[i] < arr[j] && maxDP < dp[j]) {
                    maxDP = dp[j];
                }
            }
            dp[i] = maxDP + 1;
            result = Math.max(result, dp[i]);
        }
        System.out.println(result);
    }
}
