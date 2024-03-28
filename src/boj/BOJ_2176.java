package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 백준 2176번: 합리적인 이동경로
public class BOJ_2176 {

    static int N, M;
    static List<Node>[] edges;
    static int[] dist; // 모든 정점에서 T 까지의 최소 거리
    static Integer[] dp;
    static int T = 2;
    static class Node implements Comparable<Node> {
        int to, cost;

        public Node(int x, int cost) {
            this.to = x;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return cost - o.cost;
        }
    }

    public static int getDp(int s) {
        if (dp[s] != null) return dp[s];
        if (s == 2) return 1;

        dp[s] = 0;
        for (Node next: edges[s]) {
            if (dist[s] > dist[next.to]) dp[s] += getDp(next.to);
        }
        return dp[s];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dist = new int[N + 1];
        dp = new Integer[N + 1];
        edges = new List[N + 1];
        for (int i = 1; i <= N; i++) edges[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edges[a].add(new Node(b, c));
            edges[b].add(new Node(a, c));
        }
        dijkstra();
        System.out.println(getDp(1));
    }

    public static void dijkstra() {
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(T, 0));
        dist[T] = 0;

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if (dist[now.to] < now.cost) continue;

            for (Node next : edges[now.to]) {
                if (dist[next.to] > now.cost + next.cost) {
                    dist[next.to] = now.cost + next.cost;
                    pq.offer(new Node(next.to, dist[next.to]));
                }
            }
        }

    }
}
