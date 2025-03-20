package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 1261. 알고스팟
public class BOJ_1261 {
    static int N, M;
    static int[][] map;
    static int[][] visit;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    private static int BFS() {
        Deque<Node> dq = new LinkedList<>();
        dq.offerFirst(new Node(0, 0));
        visit[0][0] = map[0][0];

        while (!dq.isEmpty()) {
            Node now = dq.pollFirst();
            if (now.x == N - 1 && now.y == M - 1) continue;

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if (isOut(nx, ny) || visit[nx][ny] <= visit[now.x][now.y] + map[nx][ny]) continue;
                visit[nx][ny] = visit[now.x][now.y] + map[nx][ny];
                if (map[nx][ny] == 0) dq.offerFirst(new Node(nx, ny));
                else dq.offerLast(new Node(nx, ny));
            }
        }

        return visit[N - 1][M - 1];
    }

    private static boolean isOut(int x, int y) {
        return x < 0 || y < 0 || x >= N || y >= M;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visit = new int[N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            Arrays.fill(visit[i], Integer.MAX_VALUE);
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(String.valueOf(str.charAt(j)));
            }
        }
        System.out.println(BFS());
    }

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
