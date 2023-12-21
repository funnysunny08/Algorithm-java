package 오답.이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 2512번: 예산
public class 예산 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 지방 수

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine()); // 총 예산

        Arrays.sort(arr);

        long mid = 0; // 상한액
        long left = 0;
        long right = arr[n - 1];

        while (left <= right) {
            mid = (left + right) / 2;

            long total = 0;
            for (int val : arr) {
                if (val >= mid) { // 상한액 보다 크면 상한액만큼만 배정함!
                    total += mid;
                } else {
                    total += val;
                }
            }

            if (total <= m) { // 예산 충족 가능! => 상한액 늘려보자
                left = mid + 1;
            } else { // 불가능 => 상한액 줄여야 함 ㅠㅠ
                right = mid - 1;
            }
        }

        System.out.println(left - 1);
    }
}
