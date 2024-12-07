package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 방문처리를 해도 되는 이유
 * A, B, C, D가 있을 때, A-D-페스티벌 순으로 방문하면 성공이다.
 * 이때 큐에 A와 D가 동시에 들어가게 된다면, A-D 루트를 방문 못하지 않나..?
 * 하지만 C-D 루트를 통해 큐에 들어오게 된 D가 결국엔 페스티벌로 방문할 수 있기 때문에
 */
// 9205. 맥주 마시면서 걸어가기
public class BOJ_9205 {
    static int sx, sy, ex, ey, n;
    static Node[] nodes;
    static boolean[] visited;

    private static boolean find() {
        visited = new boolean[n];
        Node festival = new Node(ex, ey, -1);
        Node start = new Node(sx, sy, -1);
        if (canGo(start, festival)) return true; // 시작 위치에서 페스티벌 위치로 바로 이동할 수 있는지 체크
        Queue<Node> q = new LinkedList<>();
        q.offer(start);
        while (!q.isEmpty()) {
            Node now = q.poll();
            if (canGo(now, festival)) return true; // 현재 위치에서 페스티벌 위치로 바로 이동할 수 있는지 체크
            if (now.i >= 0) visited[now.i] = true; // 방문 처리

            for (int i = 0; i < n; i++) {
                if (!visited[i] && canGo(now, nodes[i])) { // 방문한 적 없고, 여기서 이동할 수 있는 편의점이 있으면 큐에 삽입
                    q.offer(nodes[i]);
                }
            }
        }
        return false;
    }

    private static boolean canGo(Node n1, Node n2) {
        return (Math.abs(n1.x - n2.x) + Math.abs(n1.y - n2.y)) <= 1000;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            sx = Integer.parseInt(st.nextToken());
            sy = Integer.parseInt(st.nextToken());
            nodes = new Node[n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                nodes[i] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), i);
            }
            st = new StringTokenizer(br.readLine());
            ex = Integer.parseInt(st.nextToken());
            ey = Integer.parseInt(st.nextToken());
            if (find()) sb.append("happy\n");
            else sb.append("sad\n");
        }
        System.out.println(sb);
    }
    static class Node {
        int x, y, i;

        public Node(int x, int y, int i) {
            this.x = x;
            this.y = y;
            this.i = i;
        }
    }
}
