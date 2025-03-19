package boj.오답;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 1967. 트리의 지름
public class BOJ_1967 {
    static int N;
    static List<List<Node>> list = new ArrayList<>();
    static int answer = -1;
    static boolean[] visited;

    private static void dfs(int i, int w) {
        for (Node next : list.get(i)) {
            if (!visited[next.to]) {
                visited[next.to] = true;
                dfs(next.to, w + next.w);
                // visited False 처리 안 함 => 다시 갈 일 없음
            }
        }
        answer = Math.max(answer, w);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i <= N; i++) list.add(new ArrayList<>());

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            list.get(a).add(new Node(b, v));
            list.get(b).add(new Node(a, v));
        }

        for (int i = 1; i <= N; i++) {
            visited = new boolean[N + 1];
            visited[i] = true;
            dfs(i, 0); // 양방향으로 매핑을 해놨으니, i번 노드를 시작으로 끝까지 탐색해봄 그때의 최댓값으로 계속 갱신
        }

        System.out.println(answer);
    }

    static class Node {
        int to, w;

        public Node(int to, int w) {
            this.to = to;
            this.w = w;
        }
    }
}
