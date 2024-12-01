package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 2212. 센서
public class BOJ_2212 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        if (k >= n) {
            System.out.println(0);
            System.exit(0);
        }

        int[] arr = new int[n];
        int[] diff = new int[n - 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(arr);

        for (int i = 0; i < n - 1; i++) diff[i] = arr[i + 1] - arr[i];
        Arrays.sort(diff);

        int sum = 0;
        for (int i = 0; i < n - k; i++) sum += diff[i];
        System.out.println(sum);
    }
}
