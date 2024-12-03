package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 21758. 꿀 따기
public class BOJ_21758_2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        long[] ps = new long[n]; // 누적합
        long[] rps = new long[n]; // 역 누적합
        ps[0] = arr[0];
        rps[n - 1] = arr[n - 1];
        for (int i = 1; i < n; i++) {
            ps[i] = ps[i - 1] + arr[i];
            rps[n - i - 1] = rps[n - i] + arr[n - i -1];
        }

        long max = -1;
        // 1. bee1 left, bee2 right 고정
        for (int i = 1; i < n - 1; i++) {
            max = Math.max(max, ps[i] - ps[0] + rps[i] - rps[n - 1]);
        }

        // 2. bee1 left, hive right 고정
        for (int i = 1; i < n - 1; i++) {
            max = Math.max(max, ps[n - 1] - ps[0] + ps[n - 1] - ps[i] - arr[i]);
        }

        // 3. hive left, bee1 left 고정
        for (int i = 1; i < n - 1; i++) {
            max = Math.max(max, rps[0] - rps[n - 1] + rps[0] - rps[i] - arr[i]);
        }
        System.out.println(max);
    }
}
