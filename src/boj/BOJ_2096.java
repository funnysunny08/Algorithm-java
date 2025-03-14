package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 2096. 내려가기
public class BOJ_2096 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[] max = new int[3];
        int[] min = new int[3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x0 = Integer.parseInt(st.nextToken());
            int x1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());

            if (i == 0) {
                max[0] = min[0] = x0;
                max[1] = min[1] = x1;
                max[2] = min[2] = x2;
            } else {
                // 최대
                int m0 = max[0], m1 = max[1], m2 = max[2];
                max[0] = Math.max(m0, m1) + x0;
                max[1] = Math.max(m0, Math.max(m1, m2)) + x1;
                max[2] = Math.max(m1, m2) + x2;

                // 최소
                m0 = min[0];
                m1 = min[1];
                m2 = min[2];
                min[0] = Math.min(m0, m1) + x0;
                min[1] = Math.min(m0, Math.min(m1, m2)) + x1;
                min[2] = Math.min(m1, m2) + x2;
            }
        }

        int MAX = Math.max(max[0], Math.max(max[1], max[2]));
        int MIN = Math.min(min[0], Math.min(min[1], min[2]));
        System.out.println(MAX + " " + MIN);
    }
}
