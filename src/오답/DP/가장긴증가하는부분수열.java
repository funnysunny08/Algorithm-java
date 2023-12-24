package 오답.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 11053번: 가장 긴 증가하는 부분 수열
public class 가장긴증가하는부분수열 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[n];

        int result = -1;
        for (int i = 0; i < n; i++) {
            int max = -1; // arr[i] 보다 작은 수 중에서 dp 최대값 찾기
            // 내가 몇번째로 큰지 찾아야 함! => 자신 보다 작은 수 중에 최대를 찾아야 함!
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    max = Math.max(max, dp[j]);
                }
            }
            if (max == -1) { // 나보다 작은 수가 없음
                dp[i] = 1;
            } else {
                dp[i] = max + 1;
                result = Math.max(dp[i], result);
            }
        }
        if (result == -1) {
            System.out.println(1);
        } else {
            System.out.println(result);
        }
    }
}
