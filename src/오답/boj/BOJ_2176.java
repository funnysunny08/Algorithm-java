package 오답.boj;

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
    static List<Node>[] info;
    static int[] dist;
    static Integer[] dp;
    static class Node {
        int to, cost;

        public Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        info = new List[N + 1];
        for (int i = 1; i <= N; i++) info[i] = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            info[a].add(new Node(b, c));
            info[b].add(new Node(a, c));
        }

        dijkstra(2);

        dp = new Integer[N + 1];
        System.out.println(getDp(1));
    }

    public static int getDp(int x) {
        if (dp[x] != null) return dp[x];
        if (x == 2) return 1;

        dp[x] = 0;
        for (Node next : info[x]) {
            if (dist[x] > dist[next.to]) dp[x] += getDp(next.to);
        }
        return dp[x];
    }

    public static void dijkstra(int s) {
        dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        dist[s] = 0;
        pq.add(new Node(s, 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (dist[now.to] < now.cost) continue;

            for (Node next : info[now.to]) {
                if (dist[next.to] > now.cost + next.cost) {
                    dist[next.to] = now.cost + next.cost;
                    pq.offer(new Node(next.to, now.cost + next.cost));
                }
            }
        }
    }
}
