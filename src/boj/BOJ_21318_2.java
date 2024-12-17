package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 21318. 피아노 체조
public class BOJ_21318_2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] level = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            level[i] = Integer.parseInt(st.nextToken());
        }

        int[] arr = new int[N];
        for (int i = 0; i < N - 1; i++) {
            if (level[i] > level[i + 1]) arr[i] = 1;
        }
        int[] sum = new int[N + 1];
        sum[1] = arr[0];
        for (int i = 2; i <= N; i++) {
            sum[i] = arr[i - 1] + sum[i - 1];
        }

        int Q = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            sb.append(sum[y - 1] - sum[x - 1]).append("\n");
        }
        System.out.println(sb);
    }
}
