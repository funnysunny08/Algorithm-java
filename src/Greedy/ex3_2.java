package Greedy;
import java.util.*;
// 큰 수의 법칙
public class ex3_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // N, M, K를 공백 기준으로 입력 받기
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();

        // N개의 수를 공백을 기준으로 구분하여 입력 받기
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        Arrays.sort(arr); // 입력 받은 수를 정렬하기
        int first = arr[n - 1];
        int second = arr[n-2];

        // 가장 큰 수가 더해지는 횟수 계산
        int cnt;
        if (m % (k + 1) == 0) {
            cnt = m / (k + 1);
        } else {
            cnt = (m + 1) / (k + 1);
        }

        int result = first * k * cnt + second * (m - k * cnt);
        System.out.println(result);
    }
}
