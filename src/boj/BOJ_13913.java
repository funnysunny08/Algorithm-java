package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 13913. 숨바꼭질 4
public class BOJ_13913 {
    static int MAX = 100000;
    static int N, K;
    static int[] time = new int[MAX + 1];
    static int[] prev = new int[MAX + 1];

    private static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.offer(N);
        time[N] = 0;
        prev[N] = -1;

        while (!q.isEmpty()) {
            int now = q.poll();
            if (now == K) {
                System.out.println(time[now]);
                printRoute(now);
                return;
            }

            if (now + 1 <= MAX && time[now + 1] > time[now] + 1) {
                q.offer(now + 1);
                time[now + 1] = time[now] + 1;
                prev[now + 1] = now;
            }
            if (now - 1 >= 0 && time[now - 1] > time[now] + 1) {
                q.offer(now - 1);
                time[now - 1] = time[now] + 1;
                prev[now - 1] = now;
            }
            if (now * 2 <= MAX && time[now * 2] > time[now] + 1) {
                q.offer(now * 2);
                time[now * 2] = time[now] + 1;
                prev[now * 2] = now;
            }
        }
    }

    private static void printRoute(int x) {
        StringBuilder sb = new StringBuilder();
        while (x != -1) {
            sb.insert(0, x + " ");
            x = prev[x];
        }
        System.out.println(sb.toString().trim());
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if (N >= K) {
            StringBuilder sb = new StringBuilder();
            for (int i = N; i >= K; i--) {
                sb.append(i + " ");
            }
            System.out.println(N - K);
            System.out.println(sb.toString().trim());
            return;
        }

        Arrays.fill(time, Integer.MAX_VALUE);
        bfs();
    }
}
