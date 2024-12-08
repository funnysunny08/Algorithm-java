package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_20207_3 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int[] schedules = new int[366];
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            for (int d = s; d <= e; d++) schedules[d]++;
        }

        long answer = 0;
        int w = 0;
        int h = 0;
        for (int d = 1; d <= 365; d++) {
            if (schedules[d] == 0) {
                answer += w * h;
                w = 0;
                h = 0;
            } else {
                w++;
                h = Math.max(h, schedules[d]);
            }
        }
        answer += w * h; // 마지막 365일에 일정이 있던 경우를 위해
        System.out.println(answer);
    }
}
