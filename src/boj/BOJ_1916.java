package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 1916. 최소비용 구하기
public class BOJ_1916 {

    static int N, M, s, e;
    static int[] dist;
    static List<List<Edge>> graph = new ArrayList<>();

    private static int dijkstra() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(s, 0));
        dist[s] = 0;

        while (!pq.isEmpty()) {
            Edge now = pq.poll();
            if (now.idx == e) return dist[e];
            if (now.cost > dist[now.idx]) continue;

            for (int i = 0; i < graph.get(now.idx).size(); i++) {
                Edge next = graph.get(now.idx).get(i);
                if (dist[next.idx] > now.cost + next.cost) {
                    dist[next.idx] = now.cost + next.cost;
                    pq.offer(new Edge(next.idx, dist[next.idx]));
                }
            }
        }
        return dist[e];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());
        StringTokenizer st;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.get(start).add(new Edge(end, cost));
        }
        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        System.out.println(dijkstra());
    }

    static class Edge implements Comparable<Edge> {
        int idx, cost;

        public Edge(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }
}
