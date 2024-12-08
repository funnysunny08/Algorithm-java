package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_21758_3 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        long[] sum = new long[N]; // 정방향 누적합
        long[] r_sum = new long[N]; // 역방향 누적합
        sum[0] = arr[0];
        r_sum[N - 1] = arr[N - 1];
        for (int i = 1; i < N; i++) {
            sum[i] = arr[i] + sum[i - 1];
            r_sum[N - i - 1] = arr[N - i - 1] + r_sum[N - i];
        }

        long max = -1;
        // 1. bee1 left, hive right 고정
        for (int i = 1; i < N - 1; i++) { // bee2의 위치는 i
            long total = sum[N - 1] - arr[0] - arr[i] + r_sum[i + 1];
            max = Math.max(max, total);
        }

        // 2. bee1 left, bee2 right 고정
        for (int i = 1; i < N - 1; i++) { // hive의 위치는 i
            long total = sum[i] - arr[0] + r_sum[i] - arr[N - 1];
            max = Math.max(max, total);
        }

        // 3. hive left, bee1 right 고정
        for (int i = 1; i < N - 1; i++) { // bee2의 위치는 i
            long total = sum[N - 1] - arr[N - 1] - arr[i] + sum[i - 1];
            max = Math.max(max, total);
        }
        System.out.println(max);
    }
}
