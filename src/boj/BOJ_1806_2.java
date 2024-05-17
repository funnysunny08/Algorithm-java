package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 1806번: 부분 합
public class BOJ_1806_2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        long[] sum = new long[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) sum[i] = sum[i - 1] + Integer.parseInt(st.nextToken());

        int answer = Integer.MAX_VALUE;
        int start = 0, end = 0;
        while (start <= n && end <= n) {
            if (sum[end] - sum[start] < s) { // 조건 충족 X
                end++;
            } else { // 조건 충족 O
                answer = Math.min(answer, end - start);
                start++;
            }
        }
        System.out.println(answer == Integer.MAX_VALUE ? 0 : answer);
    }
}
