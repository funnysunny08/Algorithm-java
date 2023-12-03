package 최단거리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 백준 10282번 : 해킹
public class 해킹 {

    static ArrayList<ArrayList<Node>> graph;
    static int start; // 처음으로 해킹당한 컴퓨터
    static int[] dist;
    static int result_computers;
    static int result_time;
    static int n; // 컴퓨터 개수

    static class Node {
        int idx;
        int cost;
        Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }

    static void solution() {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));

        pq.offer(new Node(start, 0));
        dist[start] = 0;
        while (!pq.isEmpty()) {
            Node curNode = pq.poll();

            if (dist[curNode.idx] < curNode.cost) continue;

            for (Node nextNode : graph.get(curNode.idx)) {
                if (dist[nextNode.idx] > curNode.cost + nextNode.cost) {
                    dist[nextNode.idx] = curNode.cost + nextNode.cost;
                    pq.offer(new Node(nextNode.idx, dist[nextNode.idx]));
                }
            }
        }
        result_computers = 0;
        result_time = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            if (dist[i] != Integer.MAX_VALUE) {
                result_computers++;
                result_time = Math.max(result_time, dist[i]);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken()); // 컴퓨터 개수
            int d = Integer.parseInt(st.nextToken()); // 의존성 개수
            start = Integer.parseInt(st.nextToken()); // 해킹 당한 컴퓨터 번호
            dist = new int[n + 1];
            graph = new ArrayList<>();
            Arrays.fill(dist, Integer.MAX_VALUE);
            for (int j = 0; j <= n; j++) {
                graph.add(new ArrayList<>());
            }
            for (int j = 0; j < d; j++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken()); // 의존하는 애
                int b = Integer.parseInt(st.nextToken()); // 의존 당하는 애
                int s = Integer.parseInt(st.nextToken()); // 감염되는 데 걸리는 시간
                graph.get(b).add(new Node(a, s));
            }
            solution();
            sb.append(result_computers).append(" ").append(result_time).append("\n");
        }
        System.out.print(sb);
    }
}
