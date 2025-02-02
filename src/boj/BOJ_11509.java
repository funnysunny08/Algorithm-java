package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 11509. 풍선 맞추기
public class BOJ_11509 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] cnt = new int[1000001];

        int answer = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int h = Integer.parseInt(st.nextToken());
            if (cnt[h] > 0) {
                cnt[h]--;
            } else {
                answer++;
            }
            cnt[h - 1]++;
        }
        System.out.println(answer);
    }
}
