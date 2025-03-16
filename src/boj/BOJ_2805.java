package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 2805. 나무 자르기
public class BOJ_2805 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 나무의 수
        int M = Integer.parseInt(st.nextToken()); // 집에 가져가야 하는 최소 나무 길이
        int[] trees = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(trees);

        int left = 0;
        int right = trees[trees.length - 1];
        int answer = 0;

        while (left <= right) {
            int mid = (left + right) / 2;

            long sum = 0;
            for (int i = 0; i < N; i++) {
                sum += trees[i] > mid ? trees[i] - mid : 0;
                if (sum >= M) break;
            }

            if (sum < M) { // 조건 만족 X => 낮춰야 함
                right = mid - 1;
            } else { // 조건 만족 O => 올려보자
                left = mid + 1;
                answer = mid;
            }
        }
        System.out.println(answer);
    }
}
