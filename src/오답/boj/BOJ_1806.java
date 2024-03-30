package 오답.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 1806번: 부분합
public class BOJ_1806 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int[] arr = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        int start = 0, end = 0, total = 0;
        int min = Integer.MAX_VALUE;
        while (start <= N && end <= N) {
            if (total >= S && min > end - start) min = end - start;

            if (total < S) total += arr[end++];
            else total -= arr[start++];
        }
        System.out.println(min == Integer.MAX_VALUE ? 0 : min);
    }
}
