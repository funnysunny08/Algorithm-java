package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 11053번: 가장 긴 증가하는 부분 수열
public class 가장긴증가하는부분수열 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 수열의 크기
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        int[] dp = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int dpMax = 1;
        for (int i = 0; i < N; i++) {
            // 현재 idx에서 dp 값을 결정해야 한다!

            int max = -1; // dp 최대값

            for (int j = 0; j < N - 1; j++) {
                if (max <= dp[j] && arr[i] > arr[j]) {
                    max = Math.max(max, dp[j]);
                }
            }
            if (max == -1) {
                dp[i] = 1;
            } else {
                dp[i] = max + 1;
                dpMax = Math.max(dpMax, max + 1);
            }
        }

        System.out.println(dpMax);
    }
}
