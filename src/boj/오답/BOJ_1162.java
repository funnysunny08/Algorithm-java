package boj.오답;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 1162. 도로포장
public class BOJ_1162 {
    static int N, M, K;
    static List<List<Edge>> list = new ArrayList<>();
    static long[][] dist;
    static final long MAX = 20000000000L;

    private static long dijkstra() {
        for (int i = 1; i <= N; i++) Arrays.fill(dist[i], MAX);
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(1, 0, 0));
        dist[1][0] = 0;

        while (!pq.isEmpty()) {
            Edge now = pq.poll();
            if (dist[now.to][now.cnt] < now.cost) continue;

            for (Edge next : list.get(now.to)) {
                if (dist[next.to][now.cnt] > next.cost + dist[now.to][now.cnt]) {
                    dist[next.to][now.cnt] = next.cost + dist[now.to][now.cnt];
                    pq.offer(new Edge(next.to, dist[next.to][now.cnt], now.cnt));
                }

                if (now.cnt + 1 <= K && dist[next.to][now.cnt + 1] > dist[now.to][now.cnt]) {
                    dist[next.to][now.cnt + 1] = dist[now.to][now.cnt];
                    pq.offer(new Edge(next.to, dist[next.to][now.cnt + 1], now.cnt + 1));
                }
            }
        }

        long min = MAX;
        for (int i = 1; i <= K; i++) min = Math.min(min, dist[N][i]);
        return min;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dist = new long[N + 1][K + 1];
        for (int i = 0; i <= N; i++) list.add(new ArrayList<>());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            list.get(a).add(new Edge(b, cost, 0));
            list.get(b).add(new Edge(a, cost, 0));
        }
        System.out.println(dijkstra());
    }

    static class Edge implements Comparable<Edge> {
        int to, cnt;
        long cost;

        public Edge(int to, long cost, int cnt) {
            this.to = to;
            this.cost = cost;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Edge o) {
            return Long.compare(cost, o.cost);
        }
    }
}
