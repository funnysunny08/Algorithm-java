package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 1912. 연속합
public class BOJ_1912 {

    static int N;
    static int[] arr;
    static long[] sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        sum = new long[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sum[i] = sum[i - 1] + arr[i]; // 누적합 계산
        }

        long min = sum[1];
        long max = sum[1];
        for (int i = 2; i <= N; i++) {
            // 최대값 후보: 지금까지 최댓값, 현재의 누적합, 현재의 누적합 - 최솟값
            // 어차피 최댓값은 가장 큰 거 - 가장 작은 거 했을 때임!!
            max = Math.max(max, Math.max(sum[i], sum[i] - min));
            min = Math.min(min, sum[i]);
        }
        System.out.println(max);
    }
}
