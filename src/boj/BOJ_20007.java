package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 20007. 떡 돌리기
public class BOJ_20007 {
    static int N, M, X, Y;
    static List<List<Node>> map = new ArrayList<>();
    static int[] dist; // Y 까지의 거리

    private static int solve() {
        List<Node> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if (i == Y) continue;
            list.add(new Node(i, dist[i]));
        }
        Collections.sort(list);
        int cnt = 1;
        int sum = 0;
        for (int i = 0; i < N - 1; i++) {
            Node now = list.get(i);
            if (sum + now.cost * 2 <= X) {
                sum += now.cost * 2;
            } else {
                cnt++;
                if (now.cost * 2 > X) return -1;
                sum = now.cost * 2;
            }
        }
        return cnt;
    }

    private static void dijkstra() {
        dist = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(Y, 0));
        dist[Y] = 0;
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if (now.cost > dist[now.x]) continue;

            for (Node next : map.get(now.x)) {
                if (dist[next.x] > now.cost + next.cost) {
                    dist[next.x] = now.cost + next.cost;
                    pq.offer(new Node(next.x, dist[next.x]));
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) map.add(new ArrayList<>());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            map.get(a).add(new Node(b, c));
            map.get(b).add(new Node(a, c));
        }
        dijkstra();
        System.out.println(solve());
    }

    static class Node implements Comparable<Node>{
        int x, cost;

        public Node(int x, int cost) {
            this.x = x;
            this.cost = cost;
        }
        // 최단거리를 기준으로 오름차순 정렬합니다.
        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
}
