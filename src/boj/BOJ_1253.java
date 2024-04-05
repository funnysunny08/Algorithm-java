package boj;

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
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[N];
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(arr);

        int answer = 0;
        for (int i = 0; i < N; i++) {
            int left = 0;
            int right = N - 1; // 이때 right = i - 1 이 아니다! => 왜냐하면, arr 에 음수값이 있을 수도 있기 때문에!

            while (true) {
                if (i == left) left++;
                else if (i == right) right--;

                if (left >= right) break;

                if (arr[left] + arr[right] < arr[i]) left++;
                else if (arr[left] + arr[right] > arr[i]) right--;
                else {
                    answer++;
                    break;
                }
            }
        }
        System.out.println(answer);
    }
}
