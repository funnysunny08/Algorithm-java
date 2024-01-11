package ParametricSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 2470번: 두 용액
public class 두용액 {

    static int N;
    static long[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(arr);

        int left = 0;
        int right = N - 1;
        long min = Long.MAX_VALUE;
        int l = 0, r = 0; // 정답 담음
        while (left < right) {
            long sum = arr[left] + arr[right];
            if (Math.abs(sum) < min) {
                min = Math.abs(sum);
                l = left;
                r = right;
            }
            if (sum < 0) {
                left++;
            } else {
                right--;
            }
        }
        System.out.println(arr[l] + " " + arr[r]);
    }
}
