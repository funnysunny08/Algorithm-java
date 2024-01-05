package 오답.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 11722번: 가장 긴 감소하는 부분 수열
public class 가장긴감소하는부분수열 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[n];

        int result = -1;

        // 10 30 10 20 20 10
        // 1  1  2  2  2  3
        for (int i = 0; i < n; i++) {
            int maxDp = 0;
            for (int j = 0; j < i; j++) {
                if (arr[i] < arr[j] && dp[j] > maxDp) {
                    maxDp = dp[j];
                }
            }
            dp[i] = maxDp + 1;
            result = Math.max(result, dp[i]);
        }
        System.out.println(result);
    }
}
