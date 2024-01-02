package 오답.최단거리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 백준 1916번: 최소 비용 구하기
public class 최소비용구하기 {

    /*
    * 구하라: A번째 도시에서 B번째 도시까지 가는데 드는 최소비용 => 다익스트라 알고리즘!!
    * */

    static int N, M, A, B;
    static List<List<Node>> graph = new ArrayList<>();
    static int[] d;

    static class Node {
        int idx, cost;
        Node (int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }

    public static void dijkstra(int start, int end) {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
        pq.offer(new Node(start, 0));
        d[start] = 0;

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if (now.idx == end) break;

            if (d[now.idx] < now.cost) continue;

            for (Node next : graph.get(now.idx)) { // 현재의 노드와 연결된 노드 탐색
                int value = now.cost + next.cost; // nowNode를 통해서 nextNode를 갈 때의 비용
                if (value < d[next.idx]) {
                    d[next.idx] = value; // now를 통해서 가는 게 더 비용이 적기 때문에 갱신!
                    pq.offer(new Node(next.idx, d[next.idx]));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph.get(x).add(new Node(y, c));
        }
        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        d = new int[N + 1];
        Arrays.fill(d, Integer.MAX_VALUE);
        dijkstra(A, B);
        System.out.println(d[B]);
    }
}
