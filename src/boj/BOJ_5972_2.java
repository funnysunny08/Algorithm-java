package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_5972_2 {
    static int N, M;
    static List<List<Road>> graph = new ArrayList<>();

    private static int dijkstra() {
        PriorityQueue<Road> pq = new PriorityQueue<>((a, b) -> a.w - b.w);
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        pq.add(new Road(1, 0));
        dist[1] = 0;

        while (!pq.isEmpty()) {
            Road now = pq.poll();
            if (now.idx == N) return dist[N];
            if (now.w > dist[now.idx]) continue;

            for (int i = 0; i < graph.get(now.idx).size(); i++) {
                Road next = graph.get(now.idx).get(i);
                if (dist[next.idx] > now.w + next.w) {
                    dist[next.idx] = now.w + next.w;
                    pq.offer(new Road(next.idx, dist[next.idx]));
                }
            }
        }
        return dist[N];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph.get(s).add(new Road(e, w));
            graph.get(e).add(new Road(s, w));
        }
        System.out.println(dijkstra());
    }

    static class Road {
        int idx, w;

        public Road(int idx, int w) {
            this.idx = idx;
            this.w = w;
        }
    }
}
