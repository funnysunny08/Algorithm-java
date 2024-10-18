package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 13398. 연속합 2
public class BOJ_13398 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) arr[i] = Integer.parseInt(st.nextToken());

        int answer = arr[1];

        int[] dp1 = new int[N + 1];
        dp1[1] = arr[1];
        for (int i = 2; i <= N; i++) {
            dp1[i] = Math.max(arr[i], dp1[i - 1] + arr[i]);
            answer = Math.max(answer, dp1[i]);
        }

        int[] dp2 = new int[N + 1];
        dp2[N] = arr[N];
        for (int i = N - 1; i >= 1; i--) {
            dp2[i] = Math.max(arr[i], dp2[i + 1] + arr[i]);
        }

        for (int i = 2; i < N; i++) {
            answer = Math.max(answer, dp1[i - 1] + dp2[i + 1]);
        }
        System.out.println(answer);
    }
}
