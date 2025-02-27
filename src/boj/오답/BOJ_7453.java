package boj.오답;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 7453. 합이 0인 네 정수
public class BOJ_7453 {
    static int N;
    static int[][] arr;
    static long[] ab, cd;

    private static void makeArr() {
        ab = new long[N * N];
        cd = new long[N * N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ab[i * N + j] = arr[i][0] + arr[j][1];
                cd[i * N + j] = arr[i][2] + arr[j][3];
            }
        }

        Arrays.sort(ab);
        Arrays.sort(cd);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        arr = new int[N][4];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) arr[i][j] = Integer.parseInt(st.nextToken());
        }
        makeArr();

        long answer = 0;
        int ptr1 = 0, ptr2 = N * N - 1;
        while (ptr1 < N * N && ptr2 >= 0) {
            long sum = ab[ptr1] + cd[ptr2];

            if (sum < 0) {
                ptr1++;
            } else if (sum > 0) {
                ptr2--;
            } else { // 중복 원소 찾고 스킵을 위해
                int cnt1 = 0;
                int cnt2 = 0;
                long value1 = ab[ptr1];
                long value2 = cd[ptr2];
                while (ptr1 < N * N && ab[ptr1] == value1) {
                    ptr1++;
                    cnt1++;
                }
                while (ptr2 >= 0 && cd[ptr2] == value2) {
                    ptr2--;
                    cnt2++;
                }
                answer += (long) cnt1 * cnt2;
            }
        }

        System.out.println(answer);
    }
}
