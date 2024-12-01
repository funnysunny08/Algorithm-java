package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_20207_2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] cnt = new int[366];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            for (int j = s; j <= e; j++) {
                cnt[j]++;
            }
        }

        int sum = 0;
        int maxHeight = 0;
        int width = 0;
        for (int i = 0; i <= 365; i++) {
            if (cnt[i] == 0) {
                sum += width * maxHeight;
                maxHeight = 0;
                width = 0;
            } else {
                width++;
                maxHeight = Math.max(maxHeight, cnt[i]);
            }
        }
        sum += width * maxHeight;
        System.out.println(sum);
    }
}
