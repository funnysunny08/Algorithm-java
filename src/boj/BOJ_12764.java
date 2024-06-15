package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 12764번: 싸지방에 간 준하
public class BOJ_12764 {

    static int N;
    static int[][] info;
    static int[] cnt;
    static int[] endTime;
    static int top = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        info = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            info[i][0] = Integer.parseInt(st.nextToken());
            info[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(info, (o1, o2) -> {
            if (o1[0] == o2[0]) return o1[1] - o2[1];
            else return o1[0] - o2[0];
        });

        cnt = new int[100001];
        endTime = new int[100001];
        cnt[top]++;
        endTime[top] = info[0][1];


        for (int i = 1; i < N; i++) {
            int idx = -1;
            for (int j = 1; j <= top; j++) {
                if (endTime[j] < info[i][0]) {
                    idx = j;
                    break;
                }
            }

            if (idx != -1) {
                cnt[idx]++;
                endTime[idx] = info[i][1];
            } else {
                top++;
                cnt[top]++;
                endTime[top] = info[i][1];
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(top).append("\n");
        for (int i = 1; i <= top; i++) sb.append(cnt[i] + " ");
        System.out.println(sb);
    }
}
