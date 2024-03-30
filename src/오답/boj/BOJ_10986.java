package 오답.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 10986번: 나머지 합
public class BOJ_10986 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long result = 0;

        int[] sum = new int[N];
        long[] remainder = new long[M];
        st = new StringTokenizer(br.readLine());
        sum[0] = (Integer.parseInt(st.nextToken())) % M;
        if (sum[0] == 0) result++;
        remainder[sum[0]]++;
        for (int i = 1; i < N; i++) {
            sum[i] = (sum[i - 1] + Integer.parseInt(st.nextToken())) % M;
            if (sum[i] == 0) result++;
            remainder[sum[i]]++;
        }

        for (int i = 0; i < M; i++) {
            if (remainder[i] >= 2) result += remainder[i] * (remainder[i] - 1) / 2;
        }
        System.out.println(result);
    }
}
