package 오답.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준 15591번: MooTube (Silver)
public class BOJ_15591 {

    static int N, Q;
    static List<List<Node>> graph = new ArrayList<>();

    static class Node {
        int x, usado;
        Node (int x, int usado) {
            this.x = x;
            this.usado = usado;
        }
    }

    public static int getRecommendCount(int k, int v) {
        boolean[] visited = new boolean[N + 1];
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(v, 0));
        visited[v] = true;

        int cnt = 0;
        while (!q.isEmpty()) {
            Node currNode = q.poll();
            cnt++;

            for (Node next : graph.get(currNode.x)) {
                if (currNode.usado == 0) {
                    if (next.usado >= k) {
                        visited[next.x] = true;
                        q.add(new Node(next.x, next.usado));
                    }
                } else if (Math.min(currNode.usado, next.usado) >= k && !visited[next.x]) {
                    visited[next.x] = true;
                    q.add(new Node(next.x, Math.min(currNode.usado, next.usado)));
                }
            }
        }
        return cnt - 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            graph.get(p).add(new Node(q, r));
            graph.get(q).add(new Node(p, r));
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            sb.append(getRecommendCount(k, v)).append("\n");
        }
        System.out.println(sb);
    }
}
