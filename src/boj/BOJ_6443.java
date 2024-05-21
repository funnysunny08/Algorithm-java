package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 백준 6443번: 애너그램
public class BOJ_6443 {

    static char[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            arr = br.readLine().toCharArray();

            Arrays.sort(arr);
            sb.append(arr).append("\n");

            while (nextPermutation(arr.length)) {
                sb.append(arr).append("\n");
            }
        }
        System.out.println(sb);
    }

    public static boolean nextPermutation(int n) {
        int idx = n - 1;
        while (idx > 0 && arr[idx] <= arr[idx - 1]) idx--;

        if (idx == 0) return false;

        for (int i = n - 1; i >= idx; i--) {
            if (arr[i] > arr[idx - 1]) {
                char tmp = arr[i];
                arr[i] = arr[idx - 1];
                arr[idx - 1] = tmp;
                break;
            }
        }

        Arrays.sort(arr, idx, n);
        return true;
    }
}
