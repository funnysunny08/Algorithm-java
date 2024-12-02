package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_13164 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
        if (K >= N) {
            System.out.println(0);
            System.exit(0);
        }

        int[] diff = new int[N - 1];
        int sum = 0;
        for (int i = 0; i < N - 1; i++) {
            diff[i] = arr[i + 1] - arr[i];
            sum += diff[i];
        }
        Arrays.sort(diff);
        // K-1개의 가장 큰 차이를 빼기 위해 마지막 K-1개의 차이를 제외
        for (int i = 0; i < K - 1; i++) {
            sum -= diff[N - 2 - i]; // 가장 큰 K-1개의 차이를 빼기
        }
        System.out.println(sum);
    }
}
