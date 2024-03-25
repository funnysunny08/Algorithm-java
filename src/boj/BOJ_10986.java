package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 10986번: 나머지 합
public class BOJ_10986 {

    static int N, M;
    static int[] sum;
    static long[] modular;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        long cnt = 0;
        sum = new int[N];
        modular = new long[M];
        st = new StringTokenizer(br.readLine());
        sum[0] = Integer.parseInt(st.nextToken()) % M;
        // 1. sum[i] % M 자체가 0인 경우
        if (sum[0] == 0) cnt++;
        modular[sum[0]]++;
        for (int i = 1; i < N; i++) {
            sum[i] = (Integer.parseInt(st.nextToken()) + sum[i - 1]) % M;
            if (sum[i] == 0) cnt++;
            modular[sum[i]]++;
        }

        // 2. 나머지가 같은 그룹에서 2개씩 뽑는 경우의 수
        for (int i = 0; i < M; i++) {
            if (modular[i] < 2) continue;
            cnt += (modular[i] * (modular[i] - 1)) / 2;
        }
        System.out.println(cnt);
    }
}
