package 오답.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 백준 6443번: 애너그램
public class BOJ_6443 {

    static char[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int t = 0; t < T; t++) {
            arr = br.readLine().toCharArray();
            int size = arr.length;
            Arrays.sort(arr);
            sb.append(arr).append("\n");
            while (nextPermutation(size)) {
                sb.append(arr).append("\n");
            }
        }
        System.out.println(sb);
    }

    private static boolean nextPermutation(int size) {
        int idx = size - 1;
        boolean chk = false;
        for (; idx > 0; idx--) {
            if (arr[idx] > arr[idx - 1]) {
                chk = true;
                break;
            }
        }
        if (!chk) return false;

        for (int i = size - 1; i >= idx; i--) {
            if (arr[idx - 1] < arr[i]) {
                char tmp = arr[idx - 1];
                arr[idx - 1] = arr[i];
                arr[i] = tmp;
                break;
            }
        }

        Arrays.sort(arr, idx, size);
        return true;
    }
}
