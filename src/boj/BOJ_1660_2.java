package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 1660. 캡틴 이다솜
public class BOJ_1660_2 {
    static int N;
    static int M = 120;
    static int[] diagram = new int[M + 1];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        makeDiagram();

        int[] dp = new int[N + 1];
        dp[1] = 1;
        for (int i = 2; i <= N; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 1; j <= M; j++) {
                if (diagram[j] > i) break;
                dp[i] = Math.min(dp[i], dp[i - diagram[j]] + 1);
            }
        }
        System.out.println(dp[N]);
    }

    private static void makeDiagram() {
        int[] arr = new int[M + 1];
        arr[1] = diagram[1] = 1;
        for (int i = 2; i <= M; i++) {
            arr[i] = arr[i - 1] + i;
            diagram[i] = diagram[i - 1] + arr[i];
        }
    }
}
