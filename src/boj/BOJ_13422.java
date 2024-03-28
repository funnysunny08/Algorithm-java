package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 13422번: 도둑
public class BOJ_13422 {

    static int N, M, K;
    static int[] arr;
    static long[] sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            arr = new int[2 * N + 1];
            sum = new long[2 * N + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                arr[i] = arr[i + N] = Integer.parseInt(st.nextToken());
            }
            for (int i = 1; i <= 2 * N; i++) {
                sum[i] = sum[i - 1] + arr[i];
            }

            int cnt = 0;
            if (N == M) {
                if (sum[N] < K) cnt++;
            } else {
                for (int i = 1; i <= N; i++) {
                    if (sum[i + M - 1] - sum[i - 1] < K) cnt++;
                }
            }
            sb.append(cnt).append("\n");
        }
        System.out.println(sb);
    }
}
