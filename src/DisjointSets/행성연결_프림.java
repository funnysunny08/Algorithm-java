package DisjointSets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 백준 16398번: 행성 연결
public class 행성연결_프림 {

    static int N;
    static ArrayList<ArrayList<Node>> nodeList;
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static boolean[] visited;
    static long result = 0;
    static int count = 0;

    static class Node implements Comparable<Node> {
        int to;
        int cost;
        Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return cost - o.cost;
        }
    }

    public static void prim() {
        pq.add(new Node(1, 0));
        while (!pq.isEmpty()) {
            Node curNode = pq.poll();
            if (visited[curNode.to]) continue;
            visited[curNode.to] = true;
            result += curNode.cost;
            for (Node nextNode : nodeList.get(curNode.to)) {
                if (!visited[nextNode.to]) {
                    pq.add(nextNode);
                }
            }
            if (++count == N) {
                break;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        visited = new boolean[N + 1];
        nodeList = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            nodeList.add(new ArrayList<>());
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int x = Integer.parseInt(st.nextToken());
                if (i != j) {
                    nodeList.get(i).add(new Node(j, x));
                }
            }
        }

        prim();
        System.out.println(result);
    }
}
