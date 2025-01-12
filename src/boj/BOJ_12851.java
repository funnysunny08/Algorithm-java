package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 12851. 숨바꼭질 2
public class BOJ_12851 {
    static int MAX = 100000;
    static int N, K; // 수빈, 동생
    static int minTime = Integer.MAX_VALUE, count;
    static int[] time;

    private static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.offer(N);
        time[N] = 0;

        while (!q.isEmpty()) {
            int now = q.poll();
            if (now == K) {
                if (minTime == time[now]) {
                    count++;
                } else if (minTime > time[now]) {
                    minTime = time[now];
                    count = 1;
                }
            }

            if (minTime < time[now]) return; // ** now 방문 시간이 최소 시간보다 크면 뒤는 더 볼 필요 없음

            if (now + 1 <= MAX && time[now + 1] >= time[now] + 1) {
                time[now + 1] = time[now] + 1;
                q.offer(now + 1);
            }

            if (now - 1 >= 0 && time[now - 1] >= time[now] + 1) {
                time[now - 1] = time[now] + 1;
                q.offer(now - 1);
            }

            if (now * 2 <= MAX && time[now * 2] >= time[now] + 1) {
                time[now * 2] = time[now] + 1;
                q.offer(now *2);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        time = new int[MAX + 1];
        Arrays.fill(time, Integer.MAX_VALUE);

        if (N >= K) {
            System.out.println((N-K) + "\n1");
            return;
        }

        bfs();

        System.out.println(minTime);
        System.out.println(count);
    }
}
