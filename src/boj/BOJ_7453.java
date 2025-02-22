package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 7453. 합이 0인 네 정수
public class BOJ_7453 {
    static int N;
    static int[][] arr;
    static int[] AB;
    static int[] CD;

    private static long twoPointer() {
        int ptr1 = 0, ptr2 = N * N - 1;
        long answer = 0;
        while (ptr1 < N * N && ptr2 >= 0) {
            long sum = AB[ptr1] + CD[ptr2];
            if (sum > 0) {
                ptr2--;
            } else if (sum < 0) {
                ptr1++;
            } else {
                long cnt1 = 0, cnt2 = 0;
                long ab = AB[ptr1], cd = CD[ptr2];
                // 중복 원소 처리
                while (ptr1 < N * N && AB[ptr1] == ab) {
                    cnt1++;
                    ptr1++;
                }
                while (ptr2 >= 0 && CD[ptr2] == cd) {
                    cnt2++;
                    ptr2--;
                }
                answer += cnt1 * cnt2;
            }
        }
        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        arr = new int[4][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                arr[j][i] = Integer.parseInt(st.nextToken());
            }
        }
        makeList();
        System.out.println(twoPointer());
    }

    private static void makeList() {
        AB = new int[N * N];
        CD = new int[N * N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                AB[i * N + j] = arr[0][i] + arr[1][j];
                CD[i * N + j] = arr[2][i] + arr[3][j];
            }
        }
        Arrays.sort(AB);
        Arrays.sort(CD);
    }
}
