package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 21924. 도시 건설 (크루스칼)
public class BOJ_21924 {
    static int N, M; // 건물, 도로 개수
    static int[] parent;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();

    private static long kruskal() {
        long cost = 0;
        int count = 0;

        while (!pq.isEmpty()) {
            Edge now = pq.poll();
            if (find(now.x) != find(now.y)) {
                union(now.x, now.y);
                cost += now.cost;
                count++;
            }
            if (count == N - 1) return cost;
        }

        return -1;
    }

    private static void union(int a, int b) {
        int pA = find(a);
        int pB = find(b);
        if (pA < pB) parent[pB] = pA;
        else parent[pA] = pB;
    }

    private static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) parent[i] = i;

        long sum = 0;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            pq.offer(new Edge(x, y, cost));
            sum += cost;
        }

        long result = kruskal();
        if (result == -1) {
            System.out.println(-1);
            System.exit(0);
        }
        System.out.println(sum - result);
    }

    static class Edge implements Comparable<Edge> {
        int x, y, cost;

        public Edge(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }
}
