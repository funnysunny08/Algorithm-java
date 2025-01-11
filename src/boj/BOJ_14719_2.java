package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 14719. 빗물
public class BOJ_14719_2 {
    static int H, W;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        arr = new int[W];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; i++) arr[i] = Integer.parseInt(st.nextToken());

        int sum = 0;
        for (int i = 1; i < W - 1; i++) {
            int leftMax = i;
            int rightMax = i;

            for (int j = 0; j < i; j++) {
                if (arr[leftMax] < arr[j]) leftMax = j;
            }
            for (int j = i + 1; j < W; j++) {
                if (arr[rightMax] < arr[j]) rightMax = j;
            }
            int std = Math.min(arr[leftMax], arr[rightMax]);
            sum += std - arr[i];
        }
        System.out.println(sum);
    }
}
