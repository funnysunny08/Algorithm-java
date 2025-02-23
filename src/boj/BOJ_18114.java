package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 18114. 블랙 프라이데이
public class BOJ_18114 {
    static int N, C;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            if (arr[i] == C) {
                System.out.println(1);
                System.exit(0);
            }
        }
        Arrays.sort(arr);

        int left = 0, right = N - 1;
        while (left < right) {
            long sum = arr[left] + arr[right];

            if (sum == C) {
                System.out.println(1);
                System.exit(0);
            } else if (sum < C) { // 커져야 함
                for (int i = 0; i < left; i++) {
                    if (arr[i] + sum == C) {
                        System.out.println(1);
                        System.exit(0);
                    }
                }
                left++;
            } else { // sum > C // 작아져야 함
                right--;
            }
        }
        System.out.println(0);
    }
}
