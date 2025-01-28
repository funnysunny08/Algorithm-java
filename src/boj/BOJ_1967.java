package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 1967. 트리의 지름
public class BOJ_1967 {
    static int N;
    static List<List<Node>> nodes = new ArrayList<>();
    static int answer = -1;
    static boolean[] visited;

    private static void dfs(int node, int weight) {
        for (Node next : nodes.get(node)) {
            if (!visited[next.num]) {
                visited[next.num] = true;
                dfs(next.num, weight + next.weight);
            }
        }
        answer = Math.max(answer, weight);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i <= N; i++) nodes.add(new ArrayList<>());

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            nodes.get(s).add(new Node(e, w));
            nodes.get(e).add(new Node(s, w));
        }

        for (int i = 1; i <= N; i++) {
            visited = new boolean[N + 1];
            visited[i] = true;
            dfs(i, 0);
        }

        System.out.println(answer);
    }

    static class Node {
        int num, weight;

        public Node(int num, int weight) {
            this.num = num;
            this.weight = weight;
        }
    }
}
