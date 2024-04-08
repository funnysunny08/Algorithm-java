package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 20366번: 같이 눈사람 만들래?
public class BOJ_20366 {

    static int N;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(arr);

        System.out.println(solution());
    }

    public static int solution() {
        int diff = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int height = arr[i] + arr[j];
                int left = 0;
                int right = N - 1;

                while (left < right) {
                    if (left == i || left == j) {
                        left++;
                        continue;
                    }
                    if (right == i || right == j) {
                        right--;
                        continue;
                    }

                    int sum = arr[left] + arr[right];

                    diff = Math.min(diff, Math.abs(sum - height));

                    if (sum > height) {
                        right--;
                    } else if (sum < height) {
                        left++;
                    } else return 0;
                }
            }
        }
        return diff;
    }
}
