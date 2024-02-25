package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 3020번: 개똥벌레
public class BOJ_3020 {

    static int N, H;
    static int[] bottom;
    static int[] top;
    static int min = Integer.MAX_VALUE;
    static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st  = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        bottom = new int[N / 2];
        top = new int[N / 2];
        int t = 0, b = 0;
        for (int i = 0; i < N; i++) {
            int h = Integer.parseInt(br.readLine());
            if (i % 2 == 0) {
                bottom[b++] = h;
            } else {
                top[t++] = H - h;
            }
        }

        Arrays.sort(bottom);
        Arrays.sort(top);

        for (int i = 1; i <= H; i++) {
            int sum = 0;
            // 1. i <= bottom 개수
            sum += getGreaterAndEqualThanBottom(i);
            // 2.i > top 개수
            sum += getLessThanTop(i);
            if (sum < min) {
                cnt = 1;
                min = sum;
            } else if (sum == min) {
                cnt++;
            }
        }
        System.out.println(min + " " + cnt);
    }

    public static int getGreaterAndEqualThanBottom(int i) { // 석순가 부딪히는 횟
        int left = 0;
        int right = N / 2 - 1;
        int idx = Integer.MAX_VALUE;
        while (left <= right) {
            int middle = (left + right) / 2;

            if (i <= bottom[middle]) {
                idx = Math.min(idx, middle);
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return idx == Integer.MAX_VALUE ? 0 : N / 2 - idx;
    }

    public static int getLessThanTop(int i) { // 종유석과 부딪히는 횟수
        int left = 0;
        int right = N / 2 - 1;
        int idx = Integer.MIN_VALUE;
        while (left <= right) {
            int middle = (left + right) / 2;

            if (i > top[middle]) {
                idx = Math.max(idx, middle);
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return idx == Integer.MIN_VALUE ? 0 : idx + 1;

    }

}
