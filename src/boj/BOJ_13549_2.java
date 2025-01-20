package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 13549. 숨바꼭질 3
public class BOJ_13549_2 {
    static int N, K;
    static int MAX = 200001;
    static int[] time = new int[MAX];

    private static void play() {
        Queue<Integer> q = new LinkedList<>();
        q.offer(N);
        time[N] = 0;
        while (!q.isEmpty()) {
            int now = q.poll();
            if (now * 2 < MAX && time[now * 2] > time[now]) {
                time[now * 2] = time[now];
                q.offer(now * 2);
            }
            if (now + 1 < MAX && time[now + 1] > time[now] + 1) {
                time[now + 1] = time[now] + 1;
                q.offer(now + 1);
            }
            if (now - 1 >= 0 && time[now - 1] > time[now] + 1) {
                time[now - 1] = time[now] + 1;
                q.offer(now - 1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        Arrays.fill(time, Integer.MAX_VALUE);
        if (N >= K) {
            System.out.println(N - K);
            return;
        }
        play();
        System.out.println(time[K]);
    }
}
