package 오답.이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 2470번: 두 용액
public class 두용액 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr); // 용액 오름차순으로 정렬!

        int left = 0;
        int right = n - 1;
        int a1 = 0, a2 = 0;
        long sum = Long.MAX_VALUE;

        while (left < right) {
            long value = arr[left] + arr[right];

            if (sum > Math.abs(value)) {
                sum = Math.abs(value);
                a1 = left;
                a2 = right;
            }

            if (value == 0) break;

            if (value < 0) {
                left++;
            } else {
                right--;
            }
        }
        System.out.println(arr[a1] + " " + arr[a2]);
    }
}
