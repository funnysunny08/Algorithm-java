package 오답.최소신장트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 백준 16398번: 행성 연결 (크루스칼)
public class 행성연결_프림 {

    static int N;
    static List<List<Node>> nodeList = new ArrayList<>();
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static boolean[] visit;
    static long result;
    static int count = 0;


    static class Node implements Comparable<Node> {
        int to, cost;
        Node (int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return cost - o.cost;
        }
    }

    public static void prim() {
        pq.offer(new Node(0, 0));
        while (!pq.isEmpty()) {
            Node curNode = pq.poll();
            if (visit[curNode.to]) continue; // 해당 정점이 이미 MST에 포함되어 있다면 넘어감!
            visit[curNode.to] = true;
            result += curNode.cost;
            for (Node nextNode : nodeList.get(curNode.to)) {
                if (!visit[nextNode.to]) { // 해당 정점이 이미 MST에 포함되어 있다면 넘어감!
                    pq.offer(nextNode);
                }
            }
            if (++count == N) break;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        visit = new boolean[N];
        for (int i = 0; i < N; i++) {
            nodeList.add(new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
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
