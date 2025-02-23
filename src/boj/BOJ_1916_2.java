package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 1916. 최소비용 구하기
public class BOJ_1916_2 {
    static int N, M;
    static int A, B;
    static List<List<Node>> list = new ArrayList<>();

    private static int dijkstra() {
        // A -> B
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(A, 0));
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[A] = 0;

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if (dist[now.to] < now.cost) continue;

            for (Node next : list.get(now.to)) {
                if (dist[next.to] > now.cost + next.cost) {
                    dist[next.to] = now.cost + next.cost;
                    pq.offer(new Node(next.to, dist[next.to]));
                }
            }
        }
        return dist[B];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        for (int i = 0; i <= N; i++) list.add(new ArrayList<>());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            list.get(x).add(new Node(y, cost));
        }
        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        System.out.println(dijkstra());
    }

    static class Node implements Comparable<Node> {
        int to, cost;

        public Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return cost - o.cost;
        }
    }
}
