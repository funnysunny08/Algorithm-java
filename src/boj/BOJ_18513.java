package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

// 백준 18513번: 샘터
public class BOJ_18513 {

    static int N, K;
    static Set<Integer> visited = new HashSet<>();
    static class Node {
        int x, score;

        public Node(int x, int score) {
            this.x = x;
            this.score = score;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.score));
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int ft = Integer.parseInt(st.nextToken());
            visited.add(ft);
            pq.offer(new Node(ft, 0));
        }

        long answer = 0;
        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (!visited.contains(now.x - 1)) {
                pq.offer(new Node(now.x - 1, now.score + 1));
                visited.add(now.x - 1);
                answer += now.score + 1;
                if (visited.size() == K + N) break;
            }

            if (!visited.contains(now.x + 1)) {
                pq.offer(new Node(now.x + 1, now.score + 1));
                visited.add(now.x + 1);
                answer += now.score + 1;
                if (visited.size() == K + N) break;
            }
        }
        System.out.println(answer);
    }

}
