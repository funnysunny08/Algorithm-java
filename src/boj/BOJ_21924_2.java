package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 21924. 도시 건설 (프림)
public class BOJ_21924_2 {
    static int N, M; // 건물, 도로 개수
    static List<List<Node>> list = new ArrayList<>();

    private static long prim() {
        for (int i = 1; i <= N; i++) Collections.sort(list.get(i));

        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visit = new boolean[N + 1];
        pq.offer(new Node(1, 0));
        long sum = 0;
        int cnt = 0;

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if (visit[now.x]) continue;
            visit[now.x] = true;
            sum += now.cost;
            cnt++;
            for (Node next : list.get(now.x)) {
                if (visit[next.x]) continue;
                pq.offer(next);
            }
        }
        return cnt == N ? sum : -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        long sum = 0;
        for (int i = 0; i <= N; i++) list.add(new ArrayList<>());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            list.get(x).add(new Node(y, cost));
            list.get(y).add(new Node(x, cost));
            sum += cost;
        }
        long result = prim();
        if (result == -1) {
            System.out.println(result);
            System.exit(0);
        }
        System.out.println(sum - result);
    }

    static class Node implements Comparable<Node> {
        int x, cost;

        public Node(int x, int cost) {
            this.x = x;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}
