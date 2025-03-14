package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 2631. 줄 세우기
public class BOJ_2631 {
    static int N;
    static int[] arr;

    private static int LIS_BS() {
        int[] LIS = new int[N + 1];
        int cnt = 1;
        LIS[cnt++] = arr[1];
        for (int i = 2; i <= N; i++) { // arr[i]의 위치를 찾아주는 것!
            if (LIS[cnt - 1] < arr[i]) {
                LIS[cnt++] = arr[i];
            } else {
                LIS[lowerBound(arr, arr[i], 1, cnt + 1)] = arr[i];
            }
        }
        return cnt - 1;
    }

    private static int lowerBound(int[] arr, int value, int s, int e) {
        while (s < e) {
            int mid = s + (e - s)/2;
            if (arr[mid] >= value) {
                e = mid;
            } else {
                s = mid + 1;
            }
        }
        return s;
    }

    private static int LIS_DP() {
        int[] dp = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            dp[i] = 1;
            for (int j = 1; j < i; j++) {
                if (arr[i] > arr[j]) dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }
        int max = -1;
        for (int i : dp) max = Math.max(max, i);
        return max;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        for (int i = 1 ; i <= N; i++) arr[i] = Integer.parseInt(br.readLine());
        System.out.println(N - LIS_DP());
    }
}
