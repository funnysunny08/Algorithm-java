package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 21758번: 꿀 따기
public class BOJ_21758 {

    static int N;
    static long[] sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        sum = new long[N + 1];
        sum[1] = Integer.parseInt(st.nextToken());
        for (int i = 2; i <= N; i++) {
            sum[i] = sum[i - 1] + Integer.parseInt(st.nextToken());
        }

        long answer = -1;

        // 1. bee1, bee2 양쪽에 고정 & 2 <= hive <= N - 1
        int bee1 = 1;
        int bee2 = N;
        int hive;
        for (hive = 2; hive <= N - 1; hive++) {
            long total = sum[hive] - sum[bee1] + sum[bee2 - 1] - sum[hive - 1];
            answer = Math.max(total, answer);
        }

        // 2. 벌통 N, bee1 1에 고정 & 2 <= bee2 <= N - 1
        hive = N;
        bee1 = 1;
        for (bee2 = 2; bee2 <= N - 1; bee2++) {
            long total = sum[hive] - sum[bee2] + sum[hive] - sum[bee1] - (sum[bee2] - sum[bee2 - 1]);
            answer = Math.max(total, answer);
        }

        // 3. 벌통 1, bee2 N에 고정 & 2 <= bee1 <= N - 1
        hive = 1;
        bee2 = N;
        for (bee1 = 2; bee1 <= N - 1; bee1++) {
            long total = sum[bee1 - 1] + sum[bee2 - 1] - (sum[bee1] - sum[bee1 - 1]);
            answer = Math.max(total, answer);
        }
        System.out.println(answer);
    }
}
