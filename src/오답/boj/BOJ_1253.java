package 오답.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 1253번: 좋다
public class BOJ_1253 {

    static int N;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(arr);
        int answer = 0;
        for (int i = 0; i < N; i++) {
            // arr[i]를 만들 수 있느냐!

            int left = 0;
            int right = N - 1;
            while (left < right) {
                if (left == i) {
                    left++;
                    continue;
                }
                if (right == i) {
                    right--;
                    continue;
                }

                if (arr[left] + arr[right] > arr[i]) {
                    right--;
                } else if (arr[left] + arr[right] < arr[i]) {
                    left++;
                } else {
                    answer++;
                    break;
                }
            }
        }
        System.out.println(answer);
    }
}
