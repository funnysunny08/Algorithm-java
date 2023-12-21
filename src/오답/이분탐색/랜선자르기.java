package 오답.이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 1654번: 랜선 자르기
public class 랜선자르기 {

    /*
    * 파라메트릭 서치
    * mid => 랜선 길이
    * */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int[] arr = new int[k];
        for (int i = 0; i < k; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);


        long left = arr[k - 1] / n;
        long right = (long) arr[k - 1] + 1L;
        long mid = 0;

        while (left < right) {
            mid = (left + right) / 2;
            long sum = 0;
            for (int length : arr) {
                sum += length / mid; // 해당 랜선으로 mid 길이의 랜선 몇개를 만들 수 있느냐!
            }

            if (sum >= n) { // 가능하므로, 랜선 길이를 더 늘려보자!
                left = mid + 1;
            } else { // 불가능하므로, 랜선 길이를 줄여야 함 ㅠㅠ
                right = mid;
            }
        }
        System.out.println(left - 1);
    }
}
