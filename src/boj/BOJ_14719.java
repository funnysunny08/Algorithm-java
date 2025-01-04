package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 14719. 빗물
public class BOJ_14719 {
    static int H, W;
    static int[] height;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        height = new int[W];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; i++) {
            height[i] = Integer.parseInt(st.nextToken());
        }

        int answer = 0;
        for (int i = 1; i < W - 1; i++) {
            int leftMax = i;
            int rightMax = i;

            for (int j = 0; j < i; j++) {
                if (height[leftMax] < height[j]) leftMax = j;
            }
            for (int j = i + 1; j < W; j++) {
                if (height[rightMax] < height[j]) rightMax = j;
            }
            int std = Math.min(height[leftMax], height[rightMax]);
            answer += std - height[i];
        }
        System.out.println(answer);
    }
}
